package myApp.dao.repositoriesImpl;

import myApp.dao.repositories.ProductsRepository;
import myApp.dao.rowMappers.ProductsRowMapper;
import myApp.models.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryImpl implements ProductsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductsRowMapper productsRowMapper;

    public ProductsRepositoryImpl(JdbcTemplate jdbcTemplate, ProductsRowMapper productsRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.productsRowMapper = productsRowMapper;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO javalab_scheme.products (name, price, deleted) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setBoolean(3, product.getDeleted());
            return ps;
        }, keyHolder);
        product.setId((Long) keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE javalab_scheme.products SET deleted = true WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> getAll(int size, int page) {
        List<Product> products;
        String sql = "SELECT * FROM javalab_scheme.products WHERE deleted = false LIMIT ? OFFSET ?";

        products = jdbcTemplate.query(
                sql,
                productsRowMapper,
                size,
                page - 1
        );
        return products;
    }

    @Override
    public void delete(String name) {
        String sql = "UPDATE javalab_scheme.products SET deleted = true WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    @Override
    public Optional<Product> getByName(String name) {
        String sql = "SELECT * FROM  javalab_scheme.products WHERE name = ? AND deleted = false";
        Product product = jdbcTemplate.queryForObject(
                sql,
                productsRowMapper,
                name
        );
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> find(Long id) {
        String sql = "SELECT * FROM  javalab_scheme.products WHERE id = ? AND deleted = false";
        Product product = jdbcTemplate.queryForObject(
                sql,
                productsRowMapper,
                id
        );
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        return null;
    }
}
