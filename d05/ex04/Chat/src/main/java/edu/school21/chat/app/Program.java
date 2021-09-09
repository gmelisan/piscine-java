package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        HikariConfig cfg = new HikariConfig();
        cfg.setPoolName("pool");
        cfg.setJdbcUrl("jdbc:postgresql://localhost/chat");
        cfg.setUsername("gmelisan");
        DataSource ds = new HikariDataSource(cfg);
        UsersRepository ur = new UsersRepositoryJdbcImpl(ds);
        List<User> list = ur.findAll(0, 0);
        for (User u : list) {
            System.out.println(u);
        }
    }
}
