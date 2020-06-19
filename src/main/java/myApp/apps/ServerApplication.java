package myApp.apps;

import com.beust.jcommander.JCommander;
import myApp.config.ServerArgs;
import myApp.servers.ChatMultiServer;

public class ServerApplication {
    private final ServerArgs argsServerClass;
    private final ChatMultiServer chatMultiServer;

    public ServerApplication(ServerArgs argsServerClass, ChatMultiServer chatMultiServer) {
        this.argsServerClass = argsServerClass;
        this.chatMultiServer = chatMultiServer;
    }

    public void startRunning(String[] args) {
        JCommander.newBuilder()
                .addObject(argsServerClass)
                .args(args)
                .build();

        chatMultiServer.start(argsServerClass.getPort());
    }
}
