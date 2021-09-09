package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
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
        Scanner s = new Scanner(System.in);
        System.out.println("Enter a message ID");
        System.out.println("Type '42' for exit");
        Long id;
        while (!(id = s.nextLong()).equals(42L)) {
            Optional<Message> msg = rep.findById(id);
            System.out.println(msg.isPresent() ? msg.get() : "not found");
        }
    }
}
