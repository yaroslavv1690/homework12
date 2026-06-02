package yemelyanov.Homework23.service;

import yemelyanov.Homework23.BankAccount;
import yemelyanov.Homework23.DebitAccount;
import yemelyanov.Homework23.CreditAccount;
import yemelyanov.Homework23.exception.NotEnoughMoneyException;
import java.util.List;

public class AccountService {

    public BankAccount openDebitAccount() {
        return new DebitAccount();
    }

    public BankAccount openCreditAccount() {
        return new CreditAccount();
    }

    public void deposit(BankAccount account, long amount) {
        account.deposit(amount);
    }

    public void withdraw(BankAccount account, long amount) throws NotEnoughMoneyException {
        account.withdraw(amount);
    }

    public long getBalance(List<? extends BankAccount> accounts) {
        long total = 0;
        for (BankAccount acc : accounts) {
            total = total + acc.getBalance();
        }
        return total;
    }

    public void transfer(BankAccount from, BankAccount to, long amount) throws NotEnoughMoneyException {
        withdraw(from, amount);
        deposit(to, amount);
    }
}
