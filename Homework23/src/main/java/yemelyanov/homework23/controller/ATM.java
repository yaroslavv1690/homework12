package yemelyanov.Homework23.controller;

import yemelyanov.Homework23.BankAccount;
import yemelyanov.Homework23.User;
import yemelyanov.Homework23.service.UserService;
import yemelyanov.Homework23.service.AccountService;
import yemelyanov.Homework23.exception.NotEnoughMoneyException;
import yemelyanov.Homework23.exception.UserLoginNotFoundException;
import yemelyanov.Homework23.exception.UserAlreadyExistsException;
import yemelyanov.Homework23.exception.InvalidPasswordException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class ATM {
    private UserService userService;
    private AccountService accountService;
    private Scanner scanner;
    private User currentUser;
    private List<BankAccount> accounts;

    public ATM(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
        scanner = new Scanner(System.in);
        accounts = new ArrayList<>();
    }

    public void start() {
        while (currentUser == null) {
            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            System.out.print("> ");
            
            String choice = scanner.nextLine();
            
            try {
                switch (choice) {
                    case "1":
                        signIn();
                        break;
                    case "2":
                        signUp();
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println("Wrong choice");
                }
            } catch (UserLoginNotFoundException | InvalidPasswordException | UserAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }
        
        showMenu();
    }

    private void signIn() throws UserLoginNotFoundException, InvalidPasswordException {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        currentUser = userService.login(login, password);
    }

    private void signUp() throws UserAlreadyExistsException {
        System.out.print("Create login: ");
        String login = scanner.nextLine();
        System.out.print("Create password: ");
        String password = scanner.nextLine();
        userService.register(login, password);
        System.out.println("Registration successful");
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Open new account");
            System.out.println("5. Transfer money");
            System.out.println("6. Exit");
            System.out.print("> ");
            
            String choice = scanner.nextLine();
            
            try {
                switch (choice) {
                    case "1":
                        checkBalance();
                        break;
                    case "2":
                        deposit();
                        break;
                    case "3":
                        withdraw();
                        break;
                    case "4":
                        openAccount();
                        break;
                    case "5":
                        transfer();
                        break;
                    case "6":
                        return;
                    default:
                        System.out.println("Wrong choice");
                }
            } catch (NotEnoughMoneyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkBalance() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet");
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount acc = accounts.get(i);
            System.out.println("Account " + (i + 1) + " (" + acc.getAccountType() + "): " + acc.getBalance());
        }
        System.out.println("Total: " + accountService.getBalance(accounts));
    }

    private void deposit() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet");
            return;
        }
        System.out.print("Select account number: ");
        int accNum = Integer.parseInt(scanner.nextLine()) - 1;
        if (accNum < 0 || accNum >= accounts.size()) {
            System.out.println("Wrong account number");
            return;
        }
        System.out.print("Enter amount: ");
        long amount = Long.parseLong(scanner.nextLine());
        if (amount > 0) {
            accountService.deposit(accounts.get(accNum), amount);
        }
    }

    private void withdraw() throws NotEnoughMoneyException {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet");
            return;
        }
        System.out.print("Select account number: ");
        int accNum = Integer.parseInt(scanner.nextLine()) - 1;
        if (accNum < 0 || accNum >= accounts.size()) {
            System.out.println("Wrong account number");
            return;
        }
        System.out.print("How much money to withdraw?\n> ");
        long amount = Long.parseLong(scanner.nextLine());
        accountService.withdraw(accounts.get(accNum), amount);
        dispenseMoney(amount);
    }

    private void openAccount() {
        System.out.println("1. Debit account");
        System.out.println("2. Credit account");
        System.out.print("> ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            accounts.add(accountService.openDebitAccount());
            System.out.println("Debit account opened");
        } else if (choice.equals("2")) {
            accounts.add(accountService.openCreditAccount());
            System.out.println("Credit account opened");
        }
    }

    private void transfer() throws NotEnoughMoneyException {
        if (accounts.size() < 2) {
            System.out.println("Need at least 2 accounts");
            return;
        }
        System.out.print("From account number: ");
        int fromNum = Integer.parseInt(scanner.nextLine()) - 1;
        System.out.print("To account number: ");
        int toNum = Integer.parseInt(scanner.nextLine()) - 1;
        if (fromNum < 0 || fromNum >= accounts.size() || toNum < 0 || toNum >= accounts.size()) {
            System.out.println("Wrong account number");
            return;
        }
        System.out.print("Amount: ");
        long amount = Long.parseLong(scanner.nextLine());
        accountService.transfer(accounts.get(fromNum), accounts.get(toNum), amount);
        System.out.println("Transfer successful");
    }

    private void dispenseMoney(long amount) {
        int[] values = {1000, 500, 100, 50, 10, 1};
        for (int value : values) {
            int count = (int)(amount / value);
            if (count > 0) {
                System.out.println(value + " x " + count);
                amount = amount - count * value;
            }
        }
    }
}
