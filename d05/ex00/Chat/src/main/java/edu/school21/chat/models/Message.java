package edu.school21.chat.models;

import java.time.*;
import java.util.Objects;

public class Message {

    private Long id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private ZonedDateTime datetime;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text='" + text + '\'' +
                ", datetime=" + datetime +
                '}';
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
