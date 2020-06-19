package myApp.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import myApp.dto.ResponseDto;
import myApp.models.Product;
import myApp.protocol.Request;
import myApp.protocol.commands.*;
import myApp.protocol.loginLogout.LoginPayload;
import myApp.protocol.messages.MessagePayload;
import myApp.servers.ClientHandler;
import myApp.services.interfaces.*;

import javax.annotation.PostConstruct;


public class RequestDispatcherImpl implements RequestDispatcher {
    private final CommandService commandsService;
    private final HelpService helpService;
    private final MessagesService messagesService;
    private final UsersService usersService;

    public RequestDispatcherImpl(
                                 CommandService commandsService,
                                 HelpService helpService,
                                 UsersService usersService,
                                 MessagesService messagesService
    ) {
        this.commandsService = commandsService;
        this.helpService = helpService;
        this.messagesService = messagesService;
        this.usersService = usersService;
    }

    public ResponseDto dispatch(Request request, ClientHandler clientHandler) {
        ResponseDto responseDto = null;
        switch (request.getHeader().get("typ")) {
            case "help": {
                responseDto = helpService.help();
                break;
            }
            case "login": {
                LoginPayload loginPayload = (LoginPayload) request.getPayload();
                responseDto = usersService.login(loginPayload.getEmail(), loginPayload.getPassword(), clientHandler);
                break;
            }
            case "logout": {
                usersService.logout(clientHandler);
                break;
            }
            case "message": {
                MessagePayload messagePayload = (MessagePayload) request.getPayload();
                responseDto = messagesService.message(request.getHeader().get("bearer"), messagePayload.getText());
                break;
            }
            case "command": {
                CommandPayload commandPayload = (CommandPayload) request.getPayload();
                DecodedJWT jwt = JWT.decode(request.getHeader().get("bearer"));
                switch (commandPayload.getCommand()) {
                    case "get messages": {
                        CommandListPayload commandListPayload = (CommandListPayload) request.getPayload();
                        responseDto = commandsService.commandGetMessages(commandListPayload.getSize(), commandListPayload.getPage());
                        break;
                    }
                    case "get products": {
                        CommandListPayload commandListPayload = (CommandListPayload) request.getPayload();
                        responseDto = commandsService.commandGetProducts(commandListPayload.getSize(), commandListPayload.getPage());
                        break;
                    }
                    case "add product": {
                        CommandAddProdPayload payload = (CommandAddProdPayload) request.getPayload();
                        if (jwt.getClaims().get("role").asString().equals("ADMIN")) {
                            Product product = Product.builder()
                                    .name(payload.getProductName())
                                    .price(payload.getPrice())
                                    .deleted(false)
                                    .build();

                            responseDto = commandsService.addProduct(product);
                        } else {
                            responseDto = commandsService.authorityFail();
                        }
                        break;
                    }
                    case "remove product": {
                        CommandRemoveProdPayload payload = (CommandRemoveProdPayload) request.getPayload();
                        if (jwt.getClaims().get("role").asString().equals("ADMIN")) {
                            responseDto = commandsService.removeProduct(payload.getProductName());
                        } else {
                            responseDto = commandsService.authorityFail();
                        }
                        break;
                    }
                    case "purchase": {
                        CommandPurchasePayload payload = (CommandPurchasePayload) request.getPayload();
                        responseDto = commandsService.purchase(payload.getProductName(), jwt.getClaims().get("sub").asInt());
                        break;
                    }
                }
            }
            break;
        }
        return responseDto;
    }
}
