package edu.school21.chat.models;

import java.time.*;
import java.util.Objects;

public class Message {

    private Long id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime datetime;


    public Message(Long id, User author, Chatroom chatroom, String text, LocalDateTime datetime) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.datetime = datetime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
    public Chatroom getChatroom() { return chatroom; }
    public void setChatroom(Chatroom chatroom) { this.chatroom = chatroom; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public LocalDateTime getDatetime() { return datetime; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }


    @Override
    public String toString() {
        return "Message: {\n" +
                "    id=" + id + ",\n" +
                "    author=" + author + ",\n" +
                "    chatroom=" + chatroom + ",\n" +
                "    text='" + text + "',\n" +
                "    datetime=" + datetime + "\n" +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && author.equals(message.author) && chatroom.equals(message.chatroom)
                && text.equals(message.text) && datetime.equals(message.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, datetime);
    }
}
