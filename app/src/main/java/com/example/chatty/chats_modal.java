package com.example.chatty;

public class chats_modal {
    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public chats_modal(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }
}
