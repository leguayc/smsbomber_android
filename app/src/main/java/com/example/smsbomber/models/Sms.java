package com.example.smsbomber.models;

public class Sms {
    private String number;
    private String message;

    public Sms(String number, String message) {
        this.number = number;
        this.message = message;
    }

    public String getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }
}
