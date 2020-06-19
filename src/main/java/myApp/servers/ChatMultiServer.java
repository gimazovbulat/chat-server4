package myApp.servers;

import lombok.Data;
import myApp.services.interfaces.RequestDispatcher;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class ChatMultiServer {
    // список клиентов
    private List<ClientHandler> clients;
    private final RequestDispatcher requestDispatcher;

    public ChatMultiServer(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
        clients = new CopyOnWriteArrayList<>();
    }

    public void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // запускаем бесконечный цикл
        while (true) {
            try {
                // запускаем обработчик сообщений для каждого подключаемого клиента
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept(), this);
                clientHandler.start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}