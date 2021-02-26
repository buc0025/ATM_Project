import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {

    @Test
    void authenticateAccount() {
<<<<<<< HEAD
        TestInput testInput = new TestInput();
//        Atm atm = new Atm(new TestInput());
        Atm atm = new Atm(testInput);
        testInput.stringInputs = new String[] {/*valid account number*/ "1", /*valid pin number*/"11"};
        testInput.intInputs = new int[1]; // Selection to check balance

        atm.authenticateAccount();
        
        SystemInput systemInput = new SystemInput();
        systemInput.getString();
        assertEquals(Atm.AtmScreens.CHECK_BALANCE, atm.getCurrentScreen());
=======
>>>>>>> 929f01d2f578da5eaa33fd07cb78b414ef7a5c95
    }

    @Test
    void promptForTransactionMenu() {
    }

    @org.junit.jupiter.api.Test
    void promptForCheckBalanceScreen() {
    }

    @org.junit.jupiter.api.Test
    void promptForDepositScreen() {
    }

    @org.junit.jupiter.api.Test
    void promptAfterDeposit() {
    }

    @org.junit.jupiter.api.Test
    void promptForWithdrawalDenominations() {
    }

    @org.junit.jupiter.api.Test
    void promptCustomWithdrawal() {
    }

    @org.junit.jupiter.api.Test
    void promptAfterWithdrawal() {
    }
}