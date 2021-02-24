import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    AccountHolder accountHolder1 = new AccountHolder("Stanley", "Yang");
    Account account1 = new Account(/*accountHolder*/accountHolder1,/*accountType*/"savings",
            /*accountNumber*/"123456",
            /*pinNumber*/"11", /*accountBalance*/1000);

    @Test
    void getUserName() {
        assertEquals("Stanley Yang", account1.getUserName());
    }

    @Test
    void getAccountType() {
        assertEquals("savings", account1.getAccountType());
    }

    @Test
    void getAccountNumber() {
        assertEquals("123456", account1.getAccountNumber());
    }

    @Test
    void getPinNumber() {
        assertEquals("11", account1.getPinNumber());
    }

    @Test
    void getAccountBalance() {
        assertEquals(1000, account1.getAccountBalance());
    }

    @Test
    void debitValidAmount() {
        account1.debitAccountBalance(100);
        assertEquals(1100, account1.getAccountBalance());
    }

    @Test
    void debitNegativeAmount() {
        double balance = account1.getAccountBalance();
        account1.debitAccountBalance(-100);
        assertEquals(balance, account1.getAccountBalance());
    }

    @Test
    void creditValidAmount() {
        account1.creditAccountBalance(100);
        assertEquals(900, account1.getAccountBalance());
    }

    @Test
    void creditNegativeAmount() {
        account1.creditAccountBalance(-100);
        assertEquals(1000, account1.getAccountBalance());
    }

    @Test
    void creditIncorrectDenomination() {
        account1.creditAccountBalance(50);
        assertEquals(1000, account1.getAccountBalance());
    }

    @Test
    void isAccountLocked() {
        assertEquals(false, account1.isAccountLocked());
    }
}