package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        HikariConfig cfg = new HikariConfig();
        cfg.setPoolName("pool");
        cfg.setJdbcUrl("jdbc:postgresql://localhost/chat");
        cfg.setUsername("gmelisan");
        DataSource ds = new HikariDataSource(cfg);
        MessagesRepository rep = new MessagesRepositoryJdbcImpl(ds);

        User creator = new User(1L, "John", "1234", null, null);
        Chatroom chatroom = new Chatroom(1L, "chatroom1", creator, new ArrayList<>());
        Message message = new Message(null, creator, chatroom, "Hello!", LocalDateTime.now());
        System.out.printf("User %s write in chatroom %d message '%s'\n",
                creator.getId(), chatroom.getId(), message.getText());
        rep.save(message);
        System.out.println(rep.findById(message.getId()).orElse(null));
    }
}
