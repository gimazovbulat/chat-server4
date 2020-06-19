import myApp.dao.repositories.ProductsRepository;
import myApp.dao.repositoriesImpl.ProductsRepositoryImpl;
import myApp.dao.rowMappers.ProductsRowMapper;
import myApp.models.Product;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductsRepImplTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ProductsRowMapper productsRowMapper;


    @Test
    public void testing(){
        Product product = Product.builder()
                .name("name")
                .price(123F)
                .deleted(false)
                .build();
        ProductsRepository productsRepository = new ProductsRepositoryImpl(jdbcTemplate, productsRowMapper);
        productsRepository.save(product);
        Assert.assertNotNull(product.getId());
    }
}
