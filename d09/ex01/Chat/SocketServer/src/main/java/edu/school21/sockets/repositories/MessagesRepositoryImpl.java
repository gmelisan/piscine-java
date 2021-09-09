package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository {

    private final JdbcTemplate jdbcTemplate;

    public MessagesRepositoryImpl(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    @Override
    public Message findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * from public.messages WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Message.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM public.messages",
                BeanPropertyRowMapper.newInstance(Message.class));
    }

    @Override
    public void save(Message entity) {
        String query = "INSERT INTO public.messages(sender, text) VALUES (?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(query, Long.class, entity.getSender(), entity.getText());
        entity.setId(id);
    }

    @Override
    public void update(Message entity) {
        jdbcTemplate.update("UPDATE public.messages SET sender=?, text=?, datetime=? WHERE id=?",
                entity.getSender(), entity.getText(), entity.getDatetime(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM public.messages WHERE id=?", id);
    }
}
