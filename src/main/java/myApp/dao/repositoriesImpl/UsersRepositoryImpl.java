package myApp.dao.repositoriesImpl;

import myApp.dao.repositories.UsersRepository;
import myApp.dao.rowMappers.ProductsRowMapper;
import myApp.models.Product;
import myApp.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private final PasswordEncoder encoder;
    private final JdbcTemplate jdbcTemplate;
    private final ProductsRowMapper productsRowMapper;

    private RowMapper<User> rowMappper = (row, num) -> {
        Long id = row.getLong("id");
        String email = row.getString("email");
        String password = row.getString("password");
        String nickname = row.getString("nickname");
        User.Role role = new User.Role(row.getString("role"));
        return new User(email, password, id, nickname, role);
    };

    public UsersRepositoryImpl(PasswordEncoder encoder, JdbcTemplate jdbcTemplate, ProductsRowMapper productsRowMapper) {
        this.encoder = encoder;
        this.jdbcTemplate = jdbcTemplate;
        this.productsRowMapper = productsRowMapper;
    }

    public Optional<User> checkLogin(String login, String password) {
        String sql = "SELECT * FROM javalab_scheme.users2 u2 JOIN javalab_scheme.user_roles r " +
                "ON r.user_id = u2.id WHERE email = ?";
        User user = jdbcTemplate.queryForObject(sql, rowMappper, login);
        if (user != null) {
            if (encoder.matches(password, user.getPassword())) return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> find(Long id) {
        String sql = "SELECT * FROM javalab_scheme.users2 WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql, User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void purchase(long userId, String productName) {
        String sqlToFindId = "SELECT id FROM javalab_scheme.products WHERE name = ?";
        Product product = jdbcTemplate.queryForObject(sqlToFindId, productsRowMapper, productName);
        if (product != null) {
            String sql = "INSERT INTO javalab_scheme.purchases (user_id, product_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, userId, product.getId());
        }
    }

    @Override
    public Optional<User.Role> findRole(Long userId) {
        String sql = "SELECT role FROM javalab_scheme.user_roles WHERE user_id = ?";
        User.Role role = jdbcTemplate.queryForObject(sql, User.Role.class, userId);
        return Optional.ofNullable(role);
    }

}
