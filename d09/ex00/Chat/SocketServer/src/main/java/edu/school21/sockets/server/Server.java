package edu.school21.sockets.server;


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
    private final List<Client> clients;

    public Server(UsersService usersService) {
        this.usersService = usersService;
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
                if (input.isEmpty()) {
                    client.writeLine("Username cannot be empty");
                    client.setState(Client.State.DISCONNECTED);
                    break;
                }
                client.setLogin(input);
                client.writeLine("Enter password:");
                client.setState(Client.State.SIGNUP_PASSWORD);
                break;
            case SIGNUP_PASSWORD:
                if (input.isEmpty()) {
                    client.writeLine("Password cannot be empty");
                    client.setState(Client.State.DISCONNECTED);
                    break;
                }
                try {
                    usersService.singUp(client.getLogin(), input);
                } catch (DataAccessException | UserAlreadyExistsException e) {
                    client.writeLine(e.getMessage());
                    client.setState(Client.State.DISCONNECTED);
                    break;
                }
                client.writeLine("Successful!");
                client.setState(Client.State.CONNECTED);
                client.getSocket().close();
                break;
            case CONNECTED:
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
                        "  list - list connected users");
                break;
            case "signUp":
                client.writeLine("Enter username:");
                client.setState(Client.State.SIGNUP_USERNAME);
                break;
            case "list":
                for (Client c : clients)
                    client.writeLine(c.toString());
                break;
            default:
                client.writeLine("Unknown command");
                break;
        }
    }
}
