package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestAdmin {

    CentralBank createTestBank() {

        CentralBank testBank = new CentralBank();

        Admin testAdmin0 = new Admin("testadmin0@gmail.com", "password0", testBank);
        Admin testAdmin1 = new Admin("testadmin1@gmail.com", "password1", testBank);
        testBank.getAdminList().add(testAdmin0);
        testBank.getAdminList().add(testAdmin1);

        BankTeller testTeller0 = new BankTeller("testteller0@gmail.com", "password2", testBank);
        BankTeller testTeller1 = new BankTeller("testteller1@gmail.com", "password3", testBank);
        testBank.tellerList.add(testTeller0);
        testBank.tellerList.add(testTeller1);

        User testUser0 = new User("testuser0@gmail.com", "password4", testBank);
        User testUser1 = new User("testuser1@gmail.com", "password5", testBank);
        User testUser2 = new User("testuser2@gmail.com", "password6", testBank);
        testBank.userList.add(testUser0);
        testBank.userList.add(testUser1);
        testBank.userList.add(testUser2);

        return testBank;
    }

    CentralBank testBank = createTestBank();

    @Test
    public void testAdmin(){
        Admin admin = new Admin("admin", "password", testBank);
        assert(admin.getEmailAddress().equals("admin"));
        assert(admin.getPassword().equals("password"));
    }
    
    // checks overall balance of the bank accounts
    @Test
    public void testCheckOverallAmount(){
        CentralBank centralBank = new CentralBank();
        Admin admin = new Admin("admin", "password", testBank);
        User user1 = new User("test@test.com", "password", testBank);
        User user2 = new User("test2@test.com", "password", testBank);
        BankAccount account1 = new BankAccount(12345612, 1000);
        BankAccount account2 = new BankAccount(12343613, 2000);
        BankAccount account3 = new BankAccount(12342614, 3000);
        user1.add(account1);
        user1.add(account2);
        centralBank.getUserList().add(user1);
        assert(centralBank.getOverallAmount() == 3000);
        user2.add(account3);
        centralBank.getUserList().add(user2);
        assert(centralBank.getOverallAmount() == 6000);
        assertEquals(centralBank.getOverallAmount(), admin.checkOverallAmount(centralBank));
    }

    @Test
    public void testRequestSuspiciousAccounts(){
        Admin admin = new Admin("admin", "password", testBank);
        CentralBank centralBank = new CentralBank();
        assert(admin.requestSuspiciousAccounts(centralBank) != null);
    }

    @Test
    public void testFreezeAccount(){
        Admin admin = new Admin("admin", "password", testBank);
        BankAccount bankAccount = new BankAccount(10202243, 130);
        admin.freezeAccount(bankAccount);
        assertTrue(bankAccount.getIsFrozen()==true);
    }

    @Test
    public void testUnfreezeAccount(){
        Admin admin = new Admin("admin", "password", testBank);
        BankAccount bankAccount = new BankAccount(10202243, 130);
        admin.unfreezeAccount(bankAccount);
        assertTrue(bankAccount.getIsFrozen()==false);
    }
}
