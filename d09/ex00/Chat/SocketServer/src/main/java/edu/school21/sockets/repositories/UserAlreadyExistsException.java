package edu.school21.sockets.repositories;

public class UserAlreadyExistsException extends RuntimeException {

    String login;

    public UserAlreadyExistsException(String login) {
        this.login = login;
    }
    @Override
    public String getMessage() {
        return "User '" + login + "' already exists";
    }
}