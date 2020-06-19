package myApp.services.interfaces;

import myApp.dao.repositories.UsersRepository;
import myApp.dto.UserDto;
import myApp.servers.ClientHandler;

public interface UsersService {
    UserDto login(String email, String password, ClientHandler clientHandler);
    void logout(ClientHandler clientHandler);
    UsersRepository getUsersRepository();
}
