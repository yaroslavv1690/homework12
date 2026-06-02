package yemelyanov.Homework23;

import yemelyanov.Homework23.controller.ATM;
import yemelyanov.Homework23.service.UserService;
import yemelyanov.Homework23.service.AccountService;
import yemelyanov.Homework23.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        AccountService accountService = new AccountService();
        ATM atm = new ATM(userService, accountService);
        atm.start();
    }
}