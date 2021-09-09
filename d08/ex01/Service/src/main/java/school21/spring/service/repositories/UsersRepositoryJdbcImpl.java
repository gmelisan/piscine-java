package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "SELECT * from public.user WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
                return null;
            User user = new User(
                    id,
                    rs.getString("email"));
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String query = "SELECT * from public.user";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getLong("id"),
                        rs.getString("email")
                ));
            }
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "INSERT INTO public.user(email)" +
                    "VALUES (?) RETURNING id";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getEmail());
            ResultSet rs = statement.executeQuery();
            rs.next();
            entity.setId(rs.getLong("id"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "UPDATE public.user SET email=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "DELETE FROM public.user WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "SELECT * from public.user WHERE email=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
                return Optional.empty();
            User user = new User(
                    rs.getLong("id"),
                    email);
            connection.close();
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
