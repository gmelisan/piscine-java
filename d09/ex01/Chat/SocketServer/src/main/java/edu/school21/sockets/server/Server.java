package edu.school21.sockets.server;


import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessagesRepository;
import edu.school21.sockets.repositories.UserAlreadyExistsException;
import edu.school21.sockets.services.UsersService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component
public class Server {

    private final UsersService usersService;
    private final MessagesRepository messagesRepository;
    private final List<Client> clients;

    public Server(UsersService usersService, MessagesRepository messagesRepository) {
        this.usersService = usersService;
        this.messagesRepository = messagesRepository;
        clients = new ArrayList<>();
    }

    public void start(int port) {
        try {
        ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket);
                clients.add(client);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            clientConnected(client);
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                            clients.remove(client);
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void clientConnected(Client client) throws IOException {
        client.writeLine("Hello from Server! Type 'help' for help");
        while (true) {
            String input = client.readLine();
            if (input == null) {
                clients.remove(client);
                break ;
            }
            handleInput(client, input);
            if (client.getSocket().isClosed()) {
                clients.remove(client);
                break ;
            }
        }
    }

    private void handleInput(Client client, String input) throws IOException {
        input = input.trim();
        switch (client.getState()) {
            case DISCONNECTED:
                handleCommand(client, input);
                break;
            case SIGNUP_USERNAME:
            case SIGNIN_USERNAME:
                if (input.isEmpty()) {
                    client.writeLine("Username cannot be empty");
                    client.setState(Client.State.DISCONNECTED);
                    break;
                }
                client.setLogin(input);
                client.writeLine("Enter password:");
                if (client.getState() == Client.State.SIGNUP_USERNAME)
                    client.setState(Client.State.SIGNUP_PASSWORD);
                else
                    client.setState(Client.State.SIGNIN_PASSWORD);
                break;
            case SIGNUP_PASSWORD:
            case SIGNIN_PASSWORD:
                if (input.isEmpty()) {
                    client.writeLine("Password cannot be empty");
                    client.setState(Client.State.DISCONNECTED);
                    break;
                }
                try {
                    if (client.getState() == Client.State.SIGNUP_PASSWORD) {
                        usersService.singUp(client.getLogin(), input);
                    } else {
                        User user;
                        if ((user = usersService.signIn(client.getLogin(), input)) == null) {
                            client.writeLine("Wrong username or password");
                            client.setState(Client.State.DISCONNECTED);
                            break;
                        }
                        client.setUser(user);
                    }
                } catch (DataAccessException | UserAlreadyExistsException e) {
                    client.writeLine(e.getMessage());
                    client.setState(Client.State.DISCONNECTED);
                    break;
                }
                if (client.getState() == Client.State.SIGNUP_PASSWORD)
                    client.writeLine("Successfully signed up as '" + client.getLogin() + "'");
                client.writeLine("Start messaging");
                client.setState(Client.State.CONNECTED);
                client.setAuthenticated(true);
                break;
            case CONNECTED:
                if (input.equalsIgnoreCase("exit")) {
                    client.writeLine("You have left the chat");
                    client.setState(Client.State.DISCONNECTED);
                    client.setAuthenticated(false);
                    break;
                }
                sendMessage(client, input);
                break;
        }
    }

    private void handleCommand(Client client, String input) throws IOException {
        if (input.isEmpty())
            return ;
        switch (input) {
            case "help":
                client.writeLine("Commands:\n" +
                        "  signUp - sign up new user\n" +
                        "  signIn - sign in if already registered\n" +
                        "  list - list connected users\n" +
                        "  exit - disconnect from server");
                break;
            case "signUp":
                client.writeLine("Enter username:");
                client.setState(Client.State.SIGNUP_USERNAME);
                break;
            case "signIn":
                client.writeLine("Enter username:");
                client.setState(Client.State.SIGNIN_USERNAME);
                break;
            case "list":
                for (Client c : clients)
                    client.writeLine(c.toString());
                break;
            case "exit":
                client.writeLine("Buy");
                client.getSocket().close();
                break;
            default:
                client.writeLine("Unknown command");
                break;
        }
    }

    private void sendMessage(Client client, String message) throws IOException {
        if (message.isEmpty())
            return ;
        messagesRepository.save(new Message(null, client.getUser().getId(), message, null));
        for (Client c : clients) {
            if (c.isAuthenticated())
                c.writeLine(client.getLogin() + ": " + message);
        }
    }
}
