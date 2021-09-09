package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdChatrooms;
    private List<Chatroom> chatrooms;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
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
