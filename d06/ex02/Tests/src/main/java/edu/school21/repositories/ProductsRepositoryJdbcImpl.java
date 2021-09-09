package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String query = "SELECT * from product";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price")
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
    public Optional<Product> findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "SELECT * from product WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
                return Optional.empty();
            Product product = new Product(
                    id,
                    rs.getString("name"),
                    rs.getInt("price"));
            connection.close();
            return Optional.of(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "UPDATE product SET name=?, price=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "INSERT INTO product (name, price)" +
                    "VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.executeUpdate();
            ResultSet rs = connection.prepareStatement("CALL IDENTITY()").executeQuery();
            rs.next();
            product.setId(rs.getLong(1));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            String query = "DELETE FROM product WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
