package yemelyanov.Homework23;

import yemelyanov.Homework23.enums.AccountType;
import yemelyanov.Homework23.exception.NotEnoughMoneyException;

public class DebitAccount extends BankAccount {

    public DebitAccount() {
        this.balance = 0;
        this.accountType = AccountType.DEBIT;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.DEBIT;
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