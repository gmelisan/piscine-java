package edu.school21.chat.models;

import java.sql.Timestamp;
import java.time.*;
import java.util.Objects;

public class Message {

    private final Long id;
    private final User author;
    private final Chatroom chatroom;
    private final String text;
    private final Timestamp datetime;

    public Message(Long id, User author, Chatroom chatroom, String text, Timestamp datetime) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.datetime = datetime;
    }

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
