package edu.school21.sockets.server;

import edu.school21.sockets.models.User;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {

    enum State {
        DISCONNECTED,
        SIGNUP_USERNAME,
        SIGNUP_PASSWORD,
        SIGNIN_USERNAME,
        SIGNIN_PASSWORD,
        CONNECTED
    }

    private final Socket socket;
    private final String name;
    private State state;
    private String login;
    private boolean authenticated = false;

    private User user;

    private BufferedReader reader;
    private BufferedWriter writer;

    public Client(Socket socket) throws IOException {
        this.socket = socket;
        name = socket.getInetAddress() + ":" + socket.getPort();
        this.state = State.DISCONNECTED;
        login = "";
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public Socket getSocket() { return socket; }
    public String getName() { return name; }
    public State getState() { return state; }
    public void setState(State state) { this.state = state; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public boolean isAuthenticated() { return authenticated; }
    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public void writeLine(String str) throws IOException {
        writer.write(str + "\n");
        writer.flush();
    }

    public String readLine() throws IOException {
        writer.write("> ");
        writer.flush();
        return reader.readLine();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        if (!authenticated)
            return "[" + name + "]";
        return "[" + login + "]";
    }
}
