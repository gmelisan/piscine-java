package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        int port = parsePort(args);
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        Server server = context.getBean("server", Server.class);
        server.start(port);
    }

    private static int parsePort(String[] args) {
        String argName = "--port=";
        String message = "Expected 1 argument (--port=)";
        if (args.length != 1 || !args[0].startsWith(argName)) {
            System.err.println(message);
            System.exit(1);
        }
        return Integer.parseInt(args[0].substring(argName.length()));
    }
}
