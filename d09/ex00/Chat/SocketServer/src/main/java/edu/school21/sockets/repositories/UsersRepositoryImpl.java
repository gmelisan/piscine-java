package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    @Override
    public User findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from public.users WHERE id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM public.users",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public void save(User entity) throws UserAlreadyExistsException {
        try {
            String query = "INSERT INTO public.users(login, password) VALUES (?, ?) RETURNING id";
            Long id = jdbcTemplate.queryForObject(query, Long.class, entity.getLogin(), entity.getPassword());
            entity.setId(id);
        } catch (DataAccessException e) {
            if (e.getMessage() != null && e.getMessage().contains("already exists"))
                throw new UserAlreadyExistsException(entity.getLogin());
            else
                throw e;
        }
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE public.users SET login=?, password=? WHERE id=?", entity.getLogin(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM public.users WHERE id=?", id);
    }

    @Override
    public User findByLogin(String login) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from public.users WHERE login=?",
                    BeanPropertyRowMapper.newInstance(User.class), login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}