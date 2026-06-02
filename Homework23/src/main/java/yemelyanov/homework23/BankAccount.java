package yemelyanov.Homework23;

import yemelyanov.Homework23.enums.AccountType;
import yemelyanov.Homework23.exception.NotEnoughMoneyException;

public abstract class BankAccount {
    protected long balance;
    protected AccountType accountType;

    public abstract AccountType getAccountType();

    public abstract void deposit(long amount);

    public abstract boolean withdraw(long amount) throws NotEnoughMoneyException;

    public long getBalance() {
        return balance;
    }
}
