package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {


    @FXML
    ListView<VBox> eventList = new ListView<VBox>();

    Map<String, Vector<VBox>> eventMap = new HashMap<>();





    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }



    @FXML
    TextField nameInput;

    @FXML
    TextField locationInput;

    @FXML
    Button StreetTag, ArtTag, ShowsTag, FoodTag;

    @FXML
    Button StreetAdd, ArtAdd, ShowsAdd, FoodAdd;

    

    String tagString = "Street"; // Default


    public void streetAddEvent() {
        tagString = "Street";
    }
    public void artAddEvent() {
        tagString = "Art";
    }
    public void showsAddEvent() {
        tagString = "Shows";
    }
    public void foodAddEvent() {
        tagString = "Food";
    }

    @FXML
    private void addEvent() {
        Text name = new Text();
        name.setText(nameInput.getText());
        Text location = new Text();
        location.setText(locationInput.getText());


        

        Text tagText = new Text();
        tagText.setText("Tag: " + tagString);

        VBox v = new VBox(name, location, tagText);

        if (eventMap.containsKey(tagString)) {
            eventMap.get(tagString).add(v);
        }
        else {
            Vector<VBox> vboxVec = new Vector<VBox>();
            eventMap.put(tagString, vboxVec);
            eventMap.get(tagString).add(v);
        }
        


        nameInput.clear();
        locationInput.clear();

        showAll();
    }




    public void streetTagEvent() {
        eventList.getItems().clear();
        if (eventMap.containsKey("Street")) {
            for (VBox v : eventMap.get("Street")) {
                eventList.getItems().add(v);
                
            }
        }
    }
    public void artTagEvent() {
        eventList.getItems().clear();
        if (eventMap.containsKey("Art")) {
            for (VBox v : eventMap.get("Art")) {
                eventList.getItems().add(v);
            }
        }
    }
    public void showsTagEvent() {
        eventList.getItems().clear();
        if (eventMap.containsKey("Shows")) {
            for (VBox v : eventMap.get("Shows")) {
                eventList.getItems().add(v);
            }
        }
    }
    public void foodTagEvent() {
        eventList.getItems().clear();
        if (eventMap.containsKey("Food")) {
            for (VBox v : eventMap.get("Food")) {
                eventList.getItems().add(v);
            }
        }
    }
    

    public void showAll() {
        eventList.getItems().clear();
        if (eventMap.containsKey("Street")) {
            for (VBox v : eventMap.get("Street")) {
                eventList.getItems().add(v);
                
            }
        }
        if (eventMap.containsKey("Art")) {
            for (VBox v : eventMap.get("Art")) {
                eventList.getItems().add(v);
            }
        }
        if (eventMap.containsKey("Shows")) {
            for (VBox v : eventMap.get("Shows")) {
                eventList.getItems().add(v);
            }
        }
        if (eventMap.containsKey("Food")) {
            for (VBox v : eventMap.get("Food")) {
                eventList.getItems().add(v);
            }
        }
    }






}
