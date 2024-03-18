package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 public class TestBankTeller {

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
    
    // @Test
    // void testCloseAccount() {
    //     BankAccount account = new BankAccount(12345678, 1000.0);
    //     bankTeller.getBankAccountList().add(account);
    //     bankTeller.closeAccount(account);
    //     assertFalse(bankTeller.getBankAccountList().contains(account));
    // }

    // @Test
    // void testOpenAccount() {
    //     bankTeller.openAccount(1000.0);
    //     assertEquals(1, bankTeller.getBankAccountList().size());
    //     assertNotNull(bankTeller.getBankAccountList().get(0));
    // }
    
    // @Test
    // void testCloseAccount() {
    //     // Test closing an existing account
    //     BankAccount account = new BankAccount(12345678, 1000.0);
    //     bankTeller.getBankAccountList().add(account);
    //     bankTeller.closeAccount(account);
    //     assertFalse(bankTeller.getBankAccountList().contains(account));
        
    //     // Test closing a non-existent account
    //     BankAccount nonExistentAccount = new BankAccount(67534786, 2000.0);
    //     assertFalse(bankTeller.getBankAccountList().contains(nonExistentAccount));
    // }

    // @Test
    // void testOpenAccount() {
    //     bankTeller.openAccount(1000.0);
    //     assertEquals(1, bankTeller.getBankAccountList().size());
    //     assertNotNull(bankTeller.getBankAccountList().get(0));
        
    //     bankTeller.openAccount(0.0);
    //     assertEquals(2, bankTeller.getBankAccountList().size());
    //     assertNotNull(bankTeller.getBankAccountList().get(1));
    // }
    
    // @Test
    // void testAuthenticateUser() {
    //     assertTrue(bankTeller.authenticateUser("newUser@example.com", "password"));
    //     assertFalse(bankTeller.authenticateUser("incorrect@example.com", "password"));
        
    //     // Test authenticating with incorrect password
    //     assertFalse(bankTeller.authenticateUser("newUser@example.com", "incorrectpassword"));
    // }

    private BankTeller bankTeller;

    @BeforeEach
    void setUp() {
        CentralBank newBankAccount = new CentralBank();

        bankTeller = new BankTeller("teller@example.com", "password", testBank);
        bankTeller.getBankAccountList().add(new BankAccount(12345678, 1000.0)); // Adding a bank account for the teller
        bankTeller.getBankAccountList().add(new BankAccount(87654321, 2000.0)); // Adding another bank account for the teller
        newBankAccount.userList.add(bankTeller);
    }
    
    @Test
    void testCloseAccount() {
        BankAccount account = new BankAccount(12345678, 1000.0);
        bankTeller.closeAccount(account);
        assertFalse(bankTeller.getBankAccountList().contains(account));
        
        BankAccount nonExistentAccount = new BankAccount(67534786, 2000.0);
        assertFalse(bankTeller.getBankAccountList().contains(nonExistentAccount));
    }

    @Test
    void testOpenAccount() {
        bankTeller.openAccount(1500.0);
        assertEquals(3, bankTeller.getBankAccountList().size());
        assertNotNull(bankTeller.getBankAccountList().get(2));
        
        bankTeller.openAccount(0.0);
        assertEquals(4, bankTeller.getBankAccountList().size());
        assertNotNull(bankTeller.getBankAccountList().get(3));
    }
    
    @Test
    void testAuthenticateUser() {
    // Manually add users to the userList for testing
    CentralBank centralBank = new CentralBank();
    centralBank.getUserList().add(new User("teller@example.com", "password", testBank));
    centralBank.getUserList().add(new User("anotheruser@example.com", "anotherpassword", testBank));
    
    // Test authentication with valid and invalid credentials
    // assertTrue(bankTeller.authenticateUser("teller@example.com", "password"));
    // assertFalse(bankTeller.authenticateUser("incorrect@example.com", "password"));
    // assertFalse(bankTeller.authenticateUser("teller@example.com", "incorrectpassword"));
}

}


