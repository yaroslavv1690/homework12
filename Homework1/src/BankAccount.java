public class BankAccount {
    private String ownerName;
    private long balance;

    public BankAccount(String ownerName, long balance) {
        this.ownerName = ownerName;
        this.balance = balance;
    }

    void deposit(long amount) {
        balance = balance + amount;
    }

    boolean withdraw(long amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            return true;
        }
        return false;
    }

    long getBalance() {
        return balance;
    }
}