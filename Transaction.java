package edu.ithaca.dturnbull.bank;

import java.time.LocalDateTime;

public class Transaction {

    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER
    }
    
    protected LocalDateTime date;
    protected double amount;
    protected BankAccount transferToAccount;
    protected BankAccount account;

    /*
     * This is the constructor for transaction if the action being performed is not a transfer.
     * This is why there is not transferTo argument.
     */
    public Transaction(LocalDateTime date, double amount, BankAccount account) {
        this.date = date;
        this.amount = amount;
        this.account = account;
    }

    public double getAmount(){
        return this.amount;
    }

    public BankAccount getAccount(){
        return this.account;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    /*
     * This is the constructor for transaction if the action is transfer.
     */
    public Transaction(LocalDateTime date, double amount, BankAccount account, BankAccount transferTo) {
        this.date = date;
        this.amount = amount;
        this.transferToAccount = transferTo;
        this.account = account;
    }

    public BankAccount getTransferToAccount() {
        return transferToAccount;
    }

    public void deposit(double amount) throws Exception {
        if (account.isFrozen == true) {
            throw new Exception("Account is frozen");
        }
        if (amount > 0){
            account.balance += amount;
        }
        else {
            throw new IllegalArgumentException("Cannot deposit invalid amount");
        }
    }

    public void withdraw (double amount) throws Exception{
        if (account.isFrozen == true) {
            throw new Exception("Account is frozen");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > account.balance) {
            throw new IllegalArgumentException("Amount enter is greater than balance");
        }
        account.balance -= amount;
    }

    /*
     * Withdraws amount from account
     * Deposits amount into transferToAccount
     */
    public void transfer(double amount) throws Exception {
        if (account.isFrozen == true) {
            throw new Exception("Account is frozen");
        }
        if (transferToAccount.getIsFrozen()) {
            throw new Exception("Transfer to account is frozen");
        }
        if (amount < 0){
            throw new IllegalArgumentException("Cannot transfer negative amount");
        }
        if (amount > account.balance) {
            throw new IllegalArgumentException("Amount enter is greater than balance");
        }
        account.withdraw(amount);
        transferToAccount.deposit(amount);
    }
}
