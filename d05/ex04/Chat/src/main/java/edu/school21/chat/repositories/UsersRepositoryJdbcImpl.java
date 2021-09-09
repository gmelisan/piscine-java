package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{

    private final DataSource dataSource;
    public UsersRepositoryJdbcImpl(DataSource dataSource) { this.dataSource = dataSource; }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> list = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            String query = "WITH cte_users_with_messages AS ( " +
                    "WITH cte_users_with_chatrooms AS ( " +
                    "SELECT public.user.id as owned_id, public.chatroom.id as chatroom_owned_id, name " +
                    "FROM public.user " +
                    "INNER JOIN public.chatroom " +
                    "ON owner=public.user.id " +
                    ") " +
                    "SELECT public.user.id AS active_id, text, owned_id, chatroom_owned_id, public.message.chatroom as chatroom_active_id, name " +
                    "FROM public.user " +
                    "INNER JOIN public.message " +
                    "ON author=public.user.id " +
                    "FULL JOIN cte_users_with_chatrooms " +
                    "ON owned_id=public.user.id " +
                    ") " +
                    "SELECT public.user.id, public.user.login, public.user.password, chatroom_owned_id, chatroom_active_id, name " +
                    "FROM public.user " +
                    "FULL JOIN cte_users_with_messages ON public.user.id=active_id " +
                    "ORDER BY id ";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
                return list;
            Long id = rs.getLong("id");
            Long prev_id = id;
            out:
            while (true) {
                id = rs.getLong("id");
                User user = new User(id, rs.getString("login"), rs.getString("password"),
                        new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
                while (id.equals(prev_id)) {
                    Chatroom chatroomActive = new Chatroom(rs.getLong("chatroom_active_id"),
                            rs.getString("name"), null, null);
                    user.addChatrooms(chatroomActive);
                    Chatroom chatroomOwned = new Chatroom(rs.getLong("chatroom_owned_id"),
                            rs.getString("name"), null, null);
                    user.addCreatedChatroom(chatroomOwned);
                    if (!rs.next())
                        break out;
                    id = rs.getLong("id");
                }
                list.add(user);
                prev_id = id;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
