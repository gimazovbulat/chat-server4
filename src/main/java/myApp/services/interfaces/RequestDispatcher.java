package myApp.services.interfaces;

import myApp.dto.ResponseDto;
import myApp.protocol.Request;
import myApp.servers.ClientHandler;

public interface RequestDispatcher  {
    ResponseDto dispatch(Request request, ClientHandler clientHandler);
}
