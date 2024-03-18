package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



    // @Test
    // public void testSavingAccountConstructor() {
    //     SavingAccount account = new SavingAccount(98978771, 100.0);
    //     assertEquals(98978771, account.getAccountID());
    //     assertEquals(100.0, account.getBalance());
    // }

    // @Test
    // public void testCompoundInterest() {
    //     SavingAccount account = new SavingAccount(18787887, 100.0);
    //     account.compoundInterest();
    //     assertEquals(105.0, account.getBalance());
    // }

    // @Test
    // public void testCompoundInterestWithZeroBalance() {
    //     SavingAccount account = new SavingAccount(12376768, 0.0);
    //     account.compoundInterest();
    //     assertEquals(0.0, account.getBalance());
    // }

    // @Test
    // public void testCompoundInterestMultipleYears() {
    //     SavingAccount account = new SavingAccount(45989986, 100.0);
    //     for (int i = 0; i < 3; i++) {
    //         account.compoundInterest();
    //     }
    //     assertEquals(115.7625, account.getBalance(), 0.0001);
    // }

    // @Test
    // public void testCompoundInterestNegativeBalance() {
    //     SavingAccount account = new SavingAccount(78989889, -50.0);
    //     assertThrows(IllegalArgumentException.class, () -> account.compoundInterest());
    // }
  

public class TestSaving {

    @Test
    public void testSavingsAccount() throws Exception {
        SavingAccount account = new SavingAccount(12345678, 1000, 0.05, 500);

        // Test withdrawal within limit
        account.withdraw(200);
        assertEquals(800, account.getBalance(), 0.01);

        // Test withdrawal exceeding limit
        Exception exception = assertThrows(Exception.class, () -> account.withdraw(400));
        assertEquals("Exceeds daily withdrawal limit", exception.getMessage());

        // Test interest calculation
        account.calculateAndAddInterest();
        assertTrue(account.getBalance() > 800);
    }
}
