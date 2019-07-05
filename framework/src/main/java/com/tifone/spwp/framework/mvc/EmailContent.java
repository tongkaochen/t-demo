package com.tifone.spwp.framework.mvc;

public class EmailContent {
    private final String id;
    private final String title;
    private final String sender;
    private final String ccList;
    private final String content;

    public EmailContent(String id, String title,
                        String sender, String ccList,
                        String content) {
        this.id = id;
        this.title = title;
        this.sender = sender;
        this.ccList = ccList;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSender() {
        return sender;
    }

    public String getCcList() {
        return ccList;
    }

    public String getContent() {
        return content;
    }
}
