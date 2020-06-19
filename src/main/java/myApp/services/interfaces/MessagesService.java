package myApp.services.interfaces;

import myApp.dao.repositories.MessagesRepository;
import myApp.dto.MessageDto;

public interface MessagesService {
    MessageDto message(String token, String text);
}
