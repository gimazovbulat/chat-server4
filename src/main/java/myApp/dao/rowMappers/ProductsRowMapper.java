package myApp.dao.rowMappers;

import myApp.models.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductsRowMapper implements RowMapper<Product>{

    @Override
    public Product mapRow(ResultSet row, int rowNum) throws SQLException {
        Long id = row.getLong("id");
        String name = row.getString("name");
        Float price = row.getFloat("price");
        Boolean deleted = row.getBoolean("deleted");
        return new Product(id, name, price, deleted);
    }
}
