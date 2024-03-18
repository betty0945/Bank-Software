package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


class BankAccountTest {

@Test
void isEmailValidTest() {
    // Test with valid and invalid prefix email format
    assertTrue(BankAccount.isEmailValid("abc-d@mail.com")); // valid email address
    assertTrue(BankAccount.isEmailValid("abc@mail.com"));// valid email address
    assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));// valid email address
    assertFalse(BankAccount.isEmailValid(".abc@mail.com"));// invalid email address
    assertFalse(BankAccount.isEmailValid("abc-@mail.com"));// invalid email address
    assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));// invalid email address
    assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));// invalid email address
    assertTrue(BankAccount.isEmailValid("a@b.com")); // valid email address
    assertFalse(BankAccount.isEmailValid("")); // empty string // invalid
    // Test with valid and invalid domain email format
    assertTrue(BankAccount.isEmailValid("abc.def@mail.cc")); // valid email address
    assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));// valid email address
    assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));// valid email address
    assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));// valid email address
    assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));// invalid email address
    assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));// invalid email address
    assertFalse(BankAccount.isEmailValid("abc.def@mail"));// invalid email address
    assertFalse(BankAccount.isEmailValid("abc.def@mail"));// invalid email address
}
   
    @Test
    public void testValidate() {
        assertTrue(BankAccount.isValidPassword("password1!"));
        assertFalse(BankAccount.isValidPassword("password"));
        assertFalse(BankAccount.isValidPassword("password1"));
        assertFalse(BankAccount.isValidPassword("password!"));
        assertFalse(BankAccount.isValidPassword("pass1!"));
        assertFalse(BankAccount.isValidPassword("abc.def@mail..com"));// invalid email address
}


@Test
public void testValidAccount() {
    assertTrue(BankAccount.isValidAccountId(12248567));
    assertTrue(BankAccount.isValidAccountId(12345678));
    assertFalse(BankAccount.isValidAccountId(234764));
    assertFalse(BankAccount.isValidAccountId(234764567));
    assertFalse(BankAccount.isValidAccountId(122213333));
    assertFalse(BankAccount.isValidAccountId(122777855));
}


    @Test
    public void testWithdraw() throws Exception {
        LocalDateTime now= LocalDateTime.now();
        BankAccount account1 = new BankAccount(12345678, 400);
        //BankAccount account2= new BankAccount(23456741,100);
        Transaction transaction=new Transaction(now, 200, account1);

        transaction.withdraw(200);
        assertEquals(200.0, account1.getBalance(), "After withdrawing 200.0 from 400.0, balance should be 200.0");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> transaction.withdraw(1000.0));
        assertTrue(exception.getMessage().contains("Amount enter is greater than balance"),
                "Should throw exception for withdrawing more than balance");

        exception = assertThrows(IllegalArgumentException.class, () -> transaction.withdraw(-50.0));
        assertTrue(exception.getMessage().contains("Amount must be positive"),
                "Should throw exception for negative withdrawal amount");

        account1.isFrozen = true; // Assume isFrozen field is public or there's a method to freeze the account
        exception = assertThrows(Exception.class, () -> transaction.withdraw(50.0));
        assertTrue(exception.getMessage().contains("Account is frozen"),
                "Should throw exception for withdrawing from a frozen account");
    }
    


    @Test
    public void testDeposit() throws Exception {
        BankAccount account = new BankAccount(12345678, 100); 
        account.deposit(200.0);
        assertEquals(300.0, account.getBalance(), "After depositing 200.0, balance should be 300.0");

        account.deposit(50.0);
        assertEquals(350.0, account.getBalance(), "After depositing another 50.0, balance should be 150.0");

        Exception exception = assertThrows(IllegalArgumentException.class, ()->account.deposit(-50.0));
        assertTrue(exception.getMessage().contains("Cannot deposit invalid amount"));
        
        account.isFrozen=true;
        exception=assertThrows(Exception.class,()-> account.deposit(50.0));
        assertTrue(exception.getMessage().contains("Account is frozen"),"Should throw exception fpr depositing to a frozon account");
    }

    @Test
    public void testTransfer() throws Exception {
        BankAccount account1 = new BankAccount(12345678,400); 
        BankAccount account2 = new BankAccount(87654321, 100);
       
        account1.transfer(account2, 100.0);
       
        assertEquals(300.0, account1.getBalance(), "After transferring 100.0 from account1, balance should be 300.0");
        assertEquals(200.0, account2.getBalance(), "After receiving 100.0 to account2, balance should be 200.0");


    Exception exception = assertThrows(IllegalArgumentException.class,()-> account1.transfer(account2,700.0));
    assertTrue(exception.getMessage().contains("Amount enter is greater than balance")," Should throw exception for insufficient funds");


    exception = assertThrows(IllegalArgumentException.class, () -> account1.transfer(account2, -50.0));
    assertTrue(exception.getMessage().contains("Cannot transfer negative amount"),
            "Should throw exception for negative transfer amount");

    account1.isFrozen = true;
    exception = assertThrows(Exception.class, () -> account1.transfer(account2, 50.0));
    assertTrue(exception.getMessage().contains("Account is frozen"),
            "Should throw exception for transferring from a frozen account");

    
}
}

