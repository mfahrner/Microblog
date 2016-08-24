package com.theironyard.charlotte;

/**
 * Created by mfahrner on 8/23/16.
 */
public class Message extends User {
    String message;

    public Message(String name, String password, String message) {
        super(name, password);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
