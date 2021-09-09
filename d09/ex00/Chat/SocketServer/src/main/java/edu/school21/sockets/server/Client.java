package edu.school21.sockets.server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {

    enum State {
        DISCONNECTED,
        SIGNUP_USERNAME,
        SIGNUP_PASSWORD,
        CONNECTED
    }

    private final Socket socket;
    private final String name;
    private State state;
    private String login;

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

    public void writeLine(String str) throws IOException {
        writer.write(str + "\n");
        writer.flush();
    }

    public String readLine() throws IOException {
        writer.write("> ");
        writer.write(1);
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
        if (login.isEmpty())
            return "[" + name + "]";
        return "[" + login + "]";
    }
}
