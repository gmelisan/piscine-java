package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UserAlreadyExistsException;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User singUp(String login, String password) throws
            UserAlreadyExistsException, DataAccessException {
        User user = new User(null, login, passwordEncoder.encode(password));
        usersRepository.save(user);
        return user;
    }

    @Override
    public User signIn(String login, String password) {
        User user = usersRepository.findByLogin(login);
        if (user == null)
            return null;
        if (passwordEncoder.matches(password, user.getPassword()))
            return user;
        return null;
    }
}
