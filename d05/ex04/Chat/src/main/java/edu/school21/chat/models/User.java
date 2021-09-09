package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private final String login;
    private final String password;
    private final List<Chatroom> createdChatrooms;
    private final List<Chatroom> chatrooms;

    public User(Long id, String login, String password, List<Chatroom> createdChatrooms, List<Chatroom> chatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        if (createdChatrooms == null)
            this.createdChatrooms = new ArrayList<>();
        else
            this.createdChatrooms = createdChatrooms;
        if (chatrooms == null)
            this.chatrooms = new ArrayList<>();
        else
            this.chatrooms = chatrooms;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public void addCreatedChatroom(Chatroom cr) {
        if (!createdChatrooms.contains(cr))
            createdChatrooms.add(cr);
    };
    public void addChatrooms(Chatroom cr) {
        if (!chatrooms.contains(cr))
            chatrooms.add(cr);
    }

    @Override
    public String toString() {
        return "User:{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdChatrooms=" + createdChatrooms +
                ", chatrooms=" + chatrooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && login.equals(user.login) && password.equals(user.password)
                && Objects.equals(createdChatrooms, user.createdChatrooms) && Objects.equals(chatrooms, user.chatrooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdChatrooms, chatrooms);
    }
}
