package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {


    @FXML
    ListView<VBox> eventList = new ListView<VBox>();

    Map<VBox, Vector<String>> eventMap = new HashMap<>();





    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }



    @FXML
    TextField nameInput;

    @FXML
    TextField locationInput;

    @FXML
    ToggleButton StreetTag, ArtTag, ShowsTag, FoodTag;

    @FXML
    ToggleButton StreetAdd, ArtAdd, ShowsAdd, FoodAdd;

    



    @FXML
    private void addEvent() {
        Text name = new Text();
        name.setText(nameInput.getText());
        Text location = new Text();
        location.setText(locationInput.getText());

        Vector<String> tagvec = new Vector<String>();

        String tags = "Tags:";

        if (StreetAdd.isSelected()) {
            tagvec.add("Street");
            tags += " Street";
        }
        if (ArtAdd.isSelected()) {
            tagvec.add("Art");
            tags += " Art";
        }
        if (ShowsAdd.isSelected()) {
            tagvec.add("Shows");
            tags += " Shows";
        }
        if (FoodAdd.isSelected()) {
            tagvec.add("Food");
            tags += " Food";
        }

        Text tagText = new Text();
        tagText.setText(tags);

        VBox v = new VBox(name, location, tagText);

        eventMap.put(v, tagvec);
        
        eventList.getItems().add(v);


        nameInput.clear();
        locationInput.clear();
    }

    

    







}
