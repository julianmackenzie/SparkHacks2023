package com.example;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class ServerGUI extends Application{
    BorderPane startPane;
    Server serverConnection;
    ListView<String> listItems;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to the Server");
        Button b1 = new Button("Press to Start Server");
		// Allows for input of port number
		Label l2 = new Label("Please enter the port number");
		Font f1 = new Font(14);
		l2.setFont(f1);
		
		Label l1 = new Label("Port: ");
		l1.setFont(f1);
		TextField portEnter = new TextField("");
		
		HBox h1 = new HBox(l1, portEnter);
		h1.setSpacing(10);
		
		VBox v1 = new VBox(l2, h1, b1);
		v1.setSpacing(20);
		
		
		listItems = new ListView<>();
		
		// Creates the server scene
		Scene newScene = createServerScene();
		
		// launches the server scene
		b1.setOnAction(e->{
			b1.setDisable(true);
			primaryStage.setScene(newScene);
			primaryStage.setTitle("This is the Server");
			
			// Allows for communication using callback()
			serverConnection = new Server(data -> {
				Platform.runLater(()->{
					// Writes the data member message to the listView
					Information info = (Information)data;					
					listItems.getItems().add(info.message);
				});
			});
			// Sets port to the input, if there was input
			if (portEnter.getText() != "") {
				serverConnection.port = Integer.parseInt(portEnter.getText());
			}
			
		});
		
		// Closes threads when window is closed
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		// Allocates and sets BorderPane
		this.startPane = new BorderPane();
		startPane.setCenter(v1);
		startPane.setAlignment(v1, Pos.CENTER);
		
		// Displays the scene
		Scene scene = new Scene(startPane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    // Just for the creation of the GUI
	public Scene createServerScene() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		pane.setCenter(listItems);
	
		return new Scene(pane, 500, 400);
	} // end createServerScene()
}
