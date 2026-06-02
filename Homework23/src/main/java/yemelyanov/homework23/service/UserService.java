package yemelyanov.Homework23.service;

import yemelyanov.Homework23.User;
import yemelyanov.Homework23.repository.UserRepository;
import yemelyanov.Homework23.exception.UserLoginNotFoundException;
import yemelyanov.Homework23.exception.UserAlreadyExistsException;
import yemelyanov.Homework23.exception.InvalidPasswordException;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String login, String password) throws UserAlreadyExistsException {
        if (userRepository.existsByLogin(login)) {
            throw new UserAlreadyExistsException("User with this login already exists");
        }
        User user = new User(login, password);
        userRepository.save(user);
    }

    public User login(String login, String password) throws UserLoginNotFoundException, InvalidPasswordException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UserLoginNotFoundException("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new InvalidPasswordException("Wrong password");
        }
        return user;
    }
}