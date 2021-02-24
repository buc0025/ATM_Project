import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    Database database = new Database();
    /*
    Account1:
        Account number: "1"
        Pin number: "11"
        Balance: $100
    Account2:
        Account number: "23"
        Pin number: "22"
        Balance: $150
     */
    @Test
    void verifyAccountNumber() {
        assertEquals(true, database.verifyAccountNumber("23"), "The database doesn't contain account number.");
        assertEquals(false, database.verifyAccountNumber("12"), "The database doesn't contain account number.");
    }

    @Test
    void authenticateAccount() {
        assertEquals(true, database.authenticateAccount("1", "11"),
                "Account number and pin don't match");
        assertEquals(false, database.authenticateAccount("1", "121"),
                "Account number and pin don't match");
    }

    @Test
    void checkBalance() {
        assertEquals(100, database.checkBalance("1"));
        assertEquals(150, database.checkBalance("23"));
    }

    @Test
        // withdraw() with incorrect denominations, separate withdrawal() for correct withdrawal amount
    void withdrawValidAmount() {
        double balance = database.checkBalance("1");
        database.withdraw("1", 40);
        assertEquals(balance - 40, database.checkBalance("1"));
    }

    @Test
    void withdrawIncorrectDenomination() {
        double balance = database.checkBalance("1");
        database.withdraw("1", 50);
        assertEquals(balance, database.checkBalance("1"));
    }

    @Test
    void withdrawLessThanMinimum() {
        double balance = database.checkBalance("1");
        database.withdraw("1", 1);
        assertEquals(balance, database.checkBalance("1"));
    }

    @Test
    void depositValidAmount() {
        double balance = database.checkBalance("1");
        database.deposit("1", 40);
        assertEquals(balance + 40, database.checkBalance("1"));
    }

    @Test
    void depositNegativeAmount() {
        double balance = database.checkBalance("1");
        database.deposit("1", -10);
        assertEquals(balance, database.checkBalance("1"));
    }

    @Test
    void isAccountLocked() {
        assertEquals(false, database.isAccountLocked("1"));
        assertEquals(false, database.isAccountLocked("23"));
    }
}