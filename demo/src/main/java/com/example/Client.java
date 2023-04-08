package com.example;
import java.util.*;
import java.io.Serializable;
import java.util.function.Consumer;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Client extends Thread {
    Socket socketClient;

    String ip = "127.0.0.1";
    int port = 8000;

    int player = 0;

    ObjectOutputStream out; 
    ObjectInputStream in;

    private Consumer<Serializable> callback;

    public String gameInfo;

    Client(Consumer<Serializable> call) {
        callback = call;
        this.gameInfo = "New Information";
    }

    public void run() {
        try {
            socketClient = new Socket(ip, port);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        } catch(Exception e) {}

        while (true) {
            try {
                String receive = (String)in.readObject();
                gameInfo = receive;
                callback.accept(gameInfo);
            } catch (Exception e) {}
        } // end while()
    } // end run()

    public void sendGame() {
        try {
            out.reset();
            gameInfo = "Receiving this??";
            out.writeObject(gameInfo);
            gameInfo = "Move was sent";
            callback.accept(gameInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // end sendGame()

}
