package edu.ithaca.dturnbull.bank;

import java.util.*;

public class SavingAccount extends BankAccount {
    private double annualInterestRate;
    private double dailyWithdrawalAmount;
    private double dailyWithdrawalLimit;

    public SavingAccount(int accountID, double balance, double annualInterestRate, double dailyWithdrawalLimit) {
        super(accountID, balance);
        this.annualInterestRate = annualInterestRate;
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        this.dailyWithdrawalAmount = 0;

        // Schedule the interest calculation to run at midnight every day
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                calculateAndAddInterest();
                resetDailyWithdrawalAmount();
            }
        }, getMidnightTime(), 1000 * 60 * 60 * 24);
    }

    @Override
    protected void withdraw(double amount) throws Exception {
        if (dailyWithdrawalAmount + amount > dailyWithdrawalLimit) {
            throw new Exception("Exceeds daily withdrawal limit");
        }

        super.withdraw(amount);
        dailyWithdrawalAmount += amount;
    }

    void calculateAndAddInterest() {
        double dailyInterestRate = annualInterestRate / 365;
        try {
            deposit(balance * dailyInterestRate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetDailyWithdrawalAmount() {
        dailyWithdrawalAmount = 0;
    }

    private Date getMidnightTime() {
        Calendar midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 24);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);
        midnight.set(Calendar.MILLISECOND, 0);
        return midnight.getTime();
    }
}