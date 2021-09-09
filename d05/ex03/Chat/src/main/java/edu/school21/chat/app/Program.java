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
        Long id = 1L;
        Optional<Message> messageOptional = rep.findById(id);
        if (!messageOptional.isPresent())
            throw new RuntimeException("Message not found 1");
        Message message = messageOptional.get();
        System.out.println("Original message:");
        System.out.println(message);

        message.setText("Bye");
        //message.setAuthor(new User(22L, "", "", null, null));
        message.setDatetime(null);

        rep.update(message);
        System.out.println("Updated message:");
        messageOptional = rep.findById(id);
        if (!messageOptional.isPresent())
            throw new RuntimeException("Message not found 2");
        System.out.println(messageOptional.get());
    }
}
