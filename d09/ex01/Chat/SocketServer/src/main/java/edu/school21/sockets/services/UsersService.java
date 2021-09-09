package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;

public interface UsersService {
    User singUp(String login, String password);
    User signIn(String login, String password);
}
