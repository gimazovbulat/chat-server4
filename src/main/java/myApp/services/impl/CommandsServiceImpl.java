package myApp.services.impl;

import myApp.dao.repositories.MessagesRepository;
import myApp.dao.repositories.ProductsRepository;
import myApp.dao.repositories.UsersRepository;
import myApp.dto.*;
import myApp.models.Message;
import myApp.models.Product;
import myApp.services.interfaces.CommandService;

import java.util.ArrayList;
import java.util.List;

public class CommandsServiceImpl implements CommandService {
    private final MessagesRepository messagesRepository;
    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;

    public CommandsServiceImpl(MessagesRepository messagesRepository, ProductsRepository productsRepository, UsersRepository usersRepository) {
        this.messagesRepository = messagesRepository;
        this.productsRepository = productsRepository;
        this.usersRepository = usersRepository;
    }

    public ResponseDto commandGetMessages(int size, int page) {
        List<Message> messages = messagesRepository.getMessages(size, page);
        List<MessageDto> dtoMessages = new ArrayList<>();
        for (Message m : messages) {
            MessageDto mDto = MessageDto.builder()
                    .date(m.getDate())
                    .text(m.getText())
                    .senderNickname(m.getNickname())
                    .build();
            dtoMessages.add(mDto);
        }
        return new MessagesDto(Type.ONE, dtoMessages, "get messages");
    }

    public ProductsDto commandGetProducts(int size, int page) {
        List<Product> products = productsRepository.getAll(page, size);
        List<ProductDto> dtoProducts = new ArrayList<>();
        for (Product p : products) {
            dtoProducts.add(ProductDto.builder()
                    .name(p.getName())
                    .price(p.getPrice())
                    .build()
            );
        }
        return new ProductsDto(Type.ONE, dtoProducts, "get products");
    }

    @Override
    public ActionStatusDto addProduct(Product product) {
        productsRepository.save(product);
        return new ActionStatusDto(Type.ONE, "product successfully added!", "status");
    }

    @Override
    public ActionStatusDto removeProduct(String name) {
        productsRepository.delete(name);
        return new ActionStatusDto(Type.ONE, "product was successfully removed!", "status");
    }

    @Override
    public ResponseDto purchase(String productName, long userId) {
            usersRepository.purchase(userId, productName);
            return new ActionStatusDto(Type.ONE, "product successfully purchased", "purchase");
    }

    public ActionStatusDto authorityFail() {
        return new ActionStatusDto(Type.ONE, "you don't have enough authorities(", "status");
    }

}
