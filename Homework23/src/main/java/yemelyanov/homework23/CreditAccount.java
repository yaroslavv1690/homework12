package yemelyanov.Homework23;

import yemelyanov.Homework23.enums.AccountType;
import yemelyanov.Homework23.exception.NotEnoughMoneyException;

public class CreditAccount extends BankAccount {
    private long creditLine;

    public CreditAccount() {
        this.creditLine = 20000;
        this.balance = creditLine;
        this.accountType = AccountType.CREDIT;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CREDIT;
    }

    @Override
    public void deposit(long amount) {
        if (amount > 0) {
            balance = balance + amount;
        }
    }

    @Override
    public boolean withdraw(long amount) throws NotEnoughMoneyException {
        if (amount <= 0) {
            return false;
        }
        if (amount > balance) {
            throw new NotEnoughMoneyException("Not enough money");
        }
        balance = balance - amount;
        return true;
    }
}