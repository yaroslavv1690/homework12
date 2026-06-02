import java.util.Scanner;

public class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    void showMenu() {
        while (true) {
            System.out.println("1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Exit");
            System.out.print("> ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.println("Your balance: " + account.getBalance());
                    break;
                case "2":
                    System.out.print("Enter amount to deposit: ");
                    long depositAmount = Long.parseLong(scanner.nextLine());
                    if (depositAmount > 0) {
                        account.deposit(depositAmount);
                    }
                    break;
                case "3":
                    System.out.print("How much money to withdraw?\n> ");
                    long withdrawAmount = Long.parseLong(scanner.nextLine());
                    if (withdrawAmount > 0 && account.withdraw(withdrawAmount)) {
                        dispenseMoney(withdrawAmount);
                    } else if (withdrawAmount > 0) {
                        System.out.println("Not enough money");
                    }
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    void dispenseMoney(long amount) {
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