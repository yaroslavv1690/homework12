public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Vladik", 1000);
        ATM atm = new ATM(account);
        atm.showMenu();
    }
}