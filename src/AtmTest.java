import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {

    @Test
    void authenticateAccount() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);
        testInput.stringInputs = new String[] {/*valid account number*/ "1", /*valid pin number*/"11"};

        atm.authenticateAccount();

        assertEquals(Atm.AtmScreens.TRANSACTION, atm.getCurrentScreen());
    }

    @Test
    void promptForTransactionMenu() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);
        testInput.intInputs = new int[]{2}; // Selection to make a deposit

        atm.promptForTransactionMenu();

        assertEquals(Atm.AtmScreens.MAKE_DEPOSIT, atm.getCurrentScreen());
    }

    @Test
    void promptForCheckBalanceScreen() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);

        testInput.intInputs = new int[]{2}; // Selection to exit and go back to login screen

        atm.promptForCheckBalanceScreen();

        assertEquals(Atm.AtmScreens.LOGIN, atm.getCurrentScreen());
    }

    @org.junit.jupiter.api.Test
    void promptForDepositScreen() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);

        testInput.doubleInputs = new double[]{50}; // Selection to exit and go back to login screen

        atm.promptForDepositScreen();

        assertEquals(Atm.AtmScreens.OPTIONS_AFTER_DEPOSIT, atm.getCurrentScreen());
    }

    @org.junit.jupiter.api.Test
    void promptAfterDeposit() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);

        testInput.intInputs = new int[]{2}; // Selection to go back to transaction menu

        atm.promptAfterDeposit();

        assertEquals(Atm.AtmScreens.TRANSACTION, atm.getCurrentScreen());
    }

    @org.junit.jupiter.api.Test
    void promptForWithdrawalDenominations() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);

        testInput.intInputs = new int[]{7}; // Selection to make custom withdrawal

        atm.promptForWithdrawalDenominations();

        assertEquals(Atm.AtmScreens.CUSTOM_WITHDRAWAL, atm.getCurrentScreen());
    }

    @org.junit.jupiter.api.Test
    void promptCustomWithdrawal() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);

        testInput.doubleInputs = new double[]{50}; // Not denomination of $20, redirected back to custom withdrawal screen

        atm.promptCustomWithdrawal();

        assertEquals(Atm.AtmScreens.CUSTOM_WITHDRAWAL, atm.getCurrentScreen());
    }

    @org.junit.jupiter.api.Test
    void promptAfterWithdrawal() {
        TestInput testInput = new TestInput();
        Atm atm = new Atm(testInput);

        testInput.intInputs = new int[]{3}; // Selection to log out and back to login screen

        atm.promptAfterWithdrawal();

        assertEquals(Atm.AtmScreens.LOGIN, atm.getCurrentScreen());
    }
}