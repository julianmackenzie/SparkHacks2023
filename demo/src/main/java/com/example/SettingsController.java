package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class SettingsController {

    @FXML
    private void switchToMainView() throws IOException {
        App.setRoot("mainview");
    }
}