package myApp.dao.repositories;

import myApp.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends CrudRepository<Long, Product> {
    Optional<Product> getByName(String name);
    List<Product> getAll(int size, int page);
    void delete(String name);
}
