package com.example.smsbomber.models;

public class Contact {
    private String number;
    private String name;
    private int numberMsg;

    public Contact(String number, String name) {
        this.number = number;
        this.name = name;
        this.numberMsg = 0;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getNumberMsg() {
        return numberMsg;
    }

    public void addMessageToCount() {
        this.numberMsg++;
    }
}
