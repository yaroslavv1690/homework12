package yemelyanov.Homework23.repository;

import yemelyanov.Homework23.User;
import java.util.HashMap;

public class UserRepository {
    private HashMap<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public User findByLogin(String login) {
        return users.get(login);
    }

    public void save(User user) {
        users.put(user.getLogin(), user);
    }

    public boolean existsByLogin(String login) {
        return users.containsKey(login);
    }
}