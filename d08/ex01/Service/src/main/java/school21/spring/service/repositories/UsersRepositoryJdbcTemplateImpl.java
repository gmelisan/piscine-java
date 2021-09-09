package school21.spring.service.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from public.user WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM public.user",
                    BeanPropertyRowMapper.newInstance(User.class));
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(User entity) {
        try {
            Long id = jdbcTemplate.queryForObject("INSERT INTO public.user(email) VALUES (?) RETURNING id", Long.class, entity.getEmail());
            entity.setId(id);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try {
            jdbcTemplate.update("UPDATE public.user SET email=? WHERE id=?", entity.getEmail(), entity.getId());
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            jdbcTemplate.update("DELETE FROM public.user WHERE id=?", id);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user;
        try {
            user = jdbcTemplate.queryForObject("SELECT * from public.user WHERE email=?",
                    BeanPropertyRowMapper.newInstance(User.class), email);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return user == null ? Optional.empty() : Optional.of(user);
    }
}
