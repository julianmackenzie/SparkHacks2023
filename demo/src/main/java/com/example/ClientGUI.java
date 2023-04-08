package com.example;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.util.ArrayList;


public class ClientGUI extends Application{
    Client clientConnection;
    ListView<String> listItems;
    Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Client GUI");
        this.primaryStage = primaryStage;
		
		listItems = new ListView<>();

        TextField t1 = new TextField("type what to send");

        Button b2 = new Button("Send Message");
        b2.setOnAction(e->{
            Information i1 = new Information();
            i1.message = t1.getText();
            clientConnection.sendGame(i1);
        });
		
		// Connects to server
		Button b1 = new Button("Press to Connect to Server");
		// This button begins the main GUI
		b1.setOnAction(e->{
			b1.setDisable(true);
			
			// Creates the clientConnection with the Serializable data being the CFourInfo class
			clientConnection = new Client(data->{ // access CFourInfo from data, data is CFourInfo
				Platform.runLater(()->{
				// Accepts the data
				Information info = (Information)data;
				// Adds the message to the listview
				listItems.getItems().add(info.message);
				// Checks if there is a tie
				// Checks if somebody has won
                });
			}); // end clientProject3 creation
			// starts client thread
			clientConnection.start();

        });

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
			
			// Assigns input IP and port number if they are not empty
			
			
			primaryStage.setTitle("This is the client GUI");
			// creates scene
			Scene scene = createClientScene(b1, b2, t1);
			primaryStage.setScene(scene);
            primaryStage.show();
    }

    Scene createClientScene(Button b1, Button b2, TextField t1) {
        BorderPane r1 = new BorderPane();
        r1.setBottom(listItems);
        HBox h1 = new HBox(b1, b2);
        VBox v1 = new VBox(h1, t1);
        r1.setCenter(v1);
        return new Scene(r1, 400, 400);
    }

}
