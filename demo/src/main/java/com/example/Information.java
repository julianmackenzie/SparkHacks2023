package com.example;
import java.io.Serializable;
import java.util.*;

public class Information implements Serializable{
    String message = "Uninitialized";
    int totalPlayers;
    String event;
    String location;
    ArrayList<String> pings;
    Boolean isNew;


    Information() {
        pings = new ArrayList<>();
        totalPlayers = 1;
        isNew = false;
    }
}
