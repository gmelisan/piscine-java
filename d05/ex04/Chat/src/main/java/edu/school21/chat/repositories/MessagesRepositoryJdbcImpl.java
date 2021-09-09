package edu.school21.chat.repositories;

import com.sun.tools.corba.se.idl.constExpr.Not;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException(String message) { super(message); }
}

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;
    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            Connection conn = dataSource.getConnection();
            String query = "SELECT " +
                            "public.user.id as user_id, login, password, text, datetime, " +
                            "public.chatroom.id AS chat_id, name " +
                            "FROM public.message " +
                            "INNER JOIN public.user " +
                            "ON public.message.author=public.user.id " +
                            "AND public.message.id=? " +
                            "INNER JOIN public.chatroom " +
                            "ON public.message.chatroom=public.chatroom.id";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
                return Optional.empty();
            Timestamp timestamp = rs.getTimestamp("datetime");
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
                    timestamp != null ? timestamp.toLocalDateTime() : null
            );
            conn.close();
            return Optional.of(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message) throws NotSavedSubEntityException {
        try {
            if (message.getAuthor() == null || message.getChatroom() == null)
                throw new NotSavedSubEntityException("Neither author nor chatroom should be null");
            Connection conn = dataSource.getConnection();
            String query = "INSERT INTO public.message( " +
                    "author, chatroom, text, datetime) " +
                    "VALUES (?, ?, ?, ?) " +
                    "RETURNING id, datetime";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatroom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDatetime()));
            ResultSet rs = statement.executeQuery();
            rs.next();
            message.setId(rs.getLong("id"));
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try {
            LocalDateTime dt = message.getDatetime();
            Connection conn = dataSource.getConnection();
            String query = "UPDATE public.message " +
                    "SET author=?, chatroom=?, text=?, datetime=? " +
                    "WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getChatroom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, (dt != null ? Timestamp.valueOf(dt) : null));
            statement.setLong(5, message.getId());
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}