package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

    @FXML
    BorderPane bp = new BorderPane();


    @FXML
    ListView<VBox> eventList = new ListView<VBox>();


    Text t = new Text("Test");
    VBox v = new VBox(t);

    eventList.getItems().add(v);






}
