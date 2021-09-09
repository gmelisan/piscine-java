package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;
    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            Connection conn = dataSource.getConnection();
            String query = String.format(
                    "SELECT " +
                            "public.user.id as user_id, login, password, text, datetime, " +
                            "public.chatroom.id AS chat_id, name " +
                            "FROM public.message " +
                            "INNER JOIN public.user " +
                            "ON public.message.author=public.user.id " +
                            "AND public.message.id=%d " +
                            "INNER JOIN public.chatroom " +
                            "ON public.message.chatroom=public.chatroom.id", id);
            Statement statement = conn.createStatement();
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            if (!rs.next())
                return Optional.empty();
            Message message = new Message(
                    id,
                    new User(rs.getLong("user_id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            null, null
                            ),
                    new Chatroom(rs.getLong("chat_id"),
                            rs.getString("name"),
                            null, null
                    ),
                    rs.getString("text"),
                    rs.getTimestamp("datetime")
            );
            conn.close();
            return Optional.of(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
