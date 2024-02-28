package edu.ithaca.dturnbull.bank;


import java.util.ArrayList;
import java.util.List;


public class Admin extends User {

    public Admin(String emailAddress, String password, /*new */ CentralBank centralBank) {
        super(emailAddress, password, /*new */ centralBank);
    }


    // balance of all accounts
    public double checkOverallAmount(CentralBank centralBank){
        double amount = centralBank.getOverallAmount();
        return amount;
    }


    public List<String> requestSuspiciousAccounts(CentralBank centralBank){
        List<String> suspiciousAccounts = new ArrayList<String>();
        return suspiciousAccounts;
    }


    public void freezeAccount(BankAccount account){
        //freeze account
        account.setIsFrozen(true);
    }   


    public void unfreezeAccount(BankAccount account){
        //unfreeze account
        account.setIsFrozen(false);
    }




}
