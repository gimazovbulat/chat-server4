package programms;


import myApp.apps.ServerApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProgramChatMultiServer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ServerApplication serverApplication = context.getBean(ServerApplication.class);
        serverApplication.startRunning(args);
    }
}