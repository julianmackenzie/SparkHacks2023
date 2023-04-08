package com.example;
import java.util.*;
import java.io.Serializable;
import java.util.function.Consumer;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {
    int clientCount = 1;
    private Consumer<Serializable> callback;
    TheServer server;
    ArrayList<ClientThread> clients = new ArrayList<>();
    
    public int port = 8000;

    Server(Consumer<Serializable> call) {
        this.callback = call;
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread {
        public Information gameInfo;

        TheServer() {
            gameInfo = new Information();
        }
        
        public void run() {
            try (ServerSocket mySocket = new ServerSocket(port);) {
                while (true) {
                    ClientThread c = new ClientThread(mySocket.accept(), clientCount, gameInfo);
                    gameInfo.message = "Client has connected: Welcome Client " + clientCount;
                    callback.accept(gameInfo);
                    clients.add(c);
                    c.start();
                    clientCount++;
                }
            }
            catch (Exception e) {
                gameInfo.message = "Server didn't work";
                callback.accept(gameInfo);
            }
        }
    }

    public class ClientThread extends Thread {
        Socket connection;
        int count1;
        ObjectInputStream in;
        ObjectOutputStream out;
        public Information gameInfo;
        ClientThread(Socket s, int c1, Information info) {
            this.connection = s;
            this.count1 = c1;
            gameInfo = info;
        }

        void updateClient(Information info) {
            for (int i = 0; i < clients.size(); i++) {
                ClientThread t = clients.get(i);
                try {
                    t.out.writeObject(info);
                } catch(Exception e) {}
            }
        }

        public void run() {
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch (Exception e) {}

            updateClient(gameInfo);

            while (true) {
                try {
                    Information received = (Information)in.readObject();
                    gameInfo = received;

                    callback.accept(gameInfo);
                    updateClient(gameInfo);
                }
                catch (Exception e) {
                    gameInfo.message = "Oops, something went wrong with the socket";
                    callback.accept(gameInfo);
                    updateClient(gameInfo);
                    clients.remove(this);
                    break;
                }
            }
            
        }
    }
}