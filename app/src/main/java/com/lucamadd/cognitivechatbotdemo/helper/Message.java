package com.lucamadd.cognitivechatbotdemo.helper;

public class Message {
    private String text; // message body
    private int messageType;

    public static final int USER_MESSAGE = 1;
    public static final int BOT_MESSAGE = 2;
    public static final int BOT_THINKING = 3;


    public Message(String text, int messageType) {
        this.text = text;
        this.messageType = messageType;
    }

    public String getText() {
        return text;
    }

    public int getMessageType(){
        return messageType;
    }

}
