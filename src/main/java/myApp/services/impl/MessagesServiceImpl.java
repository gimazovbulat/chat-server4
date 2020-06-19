package myApp.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import myApp.dao.repositories.MessagesRepository;
import myApp.dto.MessageDto;
import myApp.dto.Type;
import myApp.models.Message;
import myApp.services.interfaces.MessagesService;

import java.util.Date;

public class MessagesServiceImpl implements MessagesService {
    private final MessagesRepository messagesRepository;

    public MessagesServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public MessageDto message(String token, String text) {
        DecodedJWT jwt = JWT.decode(token);
        Date date = new Date(System.currentTimeMillis());
        int userId = jwt.getClaims().get("sub").asInt();
        String nickname = jwt.getClaims().get("nick").asString();

        Message message = Message.builder()
                .nickname(nickname)
                .date(date)
                .text(text)
                .userId(userId)
                .build();

        messagesRepository.save(message);

        return new MessageDto(Type.ALL, text, date, nickname);
    }
}
