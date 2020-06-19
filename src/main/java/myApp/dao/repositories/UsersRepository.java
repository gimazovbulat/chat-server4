package myApp.dao.repositories;

import myApp.models.Product;
import myApp.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Long, User> {
    void purchase(long userId, String productName);

    Optional<User> checkLogin(String login, String password);

    Optional<User.Role> findRole(Long userId);

}

