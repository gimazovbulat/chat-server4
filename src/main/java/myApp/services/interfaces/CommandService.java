package myApp.services.interfaces;

import myApp.dto.ActionStatusDto;
import myApp.dto.ProductsDto;
import myApp.dto.ResponseDto;
import myApp.models.Product;

public interface CommandService {

    ResponseDto commandGetMessages(int size, int page);

    ProductsDto commandGetProducts(int size, int page);

    ActionStatusDto addProduct(Product product);

    ActionStatusDto removeProduct(String name);

    ResponseDto purchase(String productName, long userId);

    ActionStatusDto authorityFail();

}
