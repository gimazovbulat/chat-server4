package myApp.dao.repositoriesImpl;

import myApp.dao.repositories.MessagesRepository;
import myApp.models.Message;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryImpl implements MessagesRepository {
    private final JdbcTemplate jdbcTemplate;

    public MessagesRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Message> rowMappper = (row, rowNumber) -> {
        Long id = row.getLong("id");
        String nickname = row.getString("nickname");
        String text = row.getString("text");
        Date date = new Date(row.getTimestamp("date").getTime());
        return Message.builder()
                .id(id)
                .text(text)
                .nickname(nickname)
                .date(date)
                .build();
    };

    @Override
    public Optional<Message> find(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public void save(Message message) {
      /*  String sql = "INSERT INTO javalab_scheme.messages2 (user_id, text, date) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(sql, message.getUserId(), message.getText(), message.getDate(), keyHolder);
        message.setId((Long) keyHolder.getKey());*/
    }

    @Override
    public void delete(Long aLong) {

    }

    public List<Message> getMessages(int size, int page) {
        String sql =
                "SELECT m2.id, nickname, text, date " +
                        "FROM javalab_scheme.messages2 m2 JOIN javalab_scheme.users3 u3 " +
                        "ON m2.user_id = u3.id " +
                        "ORDER BY date LIMIT ? OFFSET ?";

        List<Message> messages = jdbcTemplate.query(sql, rowMappper, size, page - 1);
        if (messages.size() != 0) return messages;
        return Collections.emptyList();
    }
}
