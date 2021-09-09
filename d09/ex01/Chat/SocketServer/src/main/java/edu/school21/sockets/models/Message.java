package edu.school21.sockets.models;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private Long sender;
    private String text;
    private LocalDateTime datetime;

    public Message() {
        this.id = 0L;
        this.sender = 0L;
        this.text = "";
        this.datetime = null;
    }

    public Message(Long id, Long sender, String text, LocalDateTime datetime) {
        this.id = id;
        this.sender = sender;
        this.text = text;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

}
