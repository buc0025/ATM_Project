import java.util.Scanner;

public class Atm {
    private boolean isUserAuthenticated;
    private boolean isAccountAuthenticated;
    private Database database;
    private Scanner input;
    private String accountNumber;

    enum Action {
        CHECK_BALANCE, MAKE_DEPOSIT, MAKE_WITHDRAW, EXIT;
    }

    public Atm() {
        isUserAuthenticated = false;
        isAccountAuthenticated = false;
        database = new Database();
        input = new Scanner(System.in);
        accountNumber = "";
    }

    public void run() {
        while (true) {
            while (!isAccountAuthenticated) {
                authenticateAccount();
            }
        }
    }

    /**
     * This method checks to see if the database contains the account number the user inputs.
     * If the database contains the account number, it will check to see if the account is locked.
     * If the account is locked, isAccountAuthenticated is set to false and ATM will ask user to enter
     * another account number. If the database doesn't contain the user's inputted account number, it will
     * tell them it's an invalid account number and to try another input. Lastly, if the database contains
     * the account number and the account is not locked, authenticateUser() is called.
     */
    public void authenticateAccount() {
        System.out.println("Please enter account number:");
        String acctNumber = input.next();

        isAccountAuthenticated = database.verifyAccountNumber(acctNumber);

        if (isAccountAuthenticated) {
            accountNumber = acctNumber;
            if (database.isAccountLocked(accountNumber)) {
                System.out.println("Account is locked. Please contact support.");
                isAccountAuthenticated = false;
            } else {
                authenticateUser();
            }
        } else {
            System.out.println("Invalid account number. Please try again.");
        }
    }

    /**
     * This method asks the user for pin number after the database has verified that the account is valid.
     * The user is allowed 3 attempts of inputting the correct pin number before their account is locked.
     * If the user inputs the correct pin number before the third attempt, the number of attempts is reset to 0.
     * Once the user inputs the correct pin while the account is not locked, promptForTransactionMenu() is called.
     */
    public void authenticateUser() {

        while (!database.isAccountLocked(accountNumber)) {
            System.out.println("Please enter pin number:");
            String pinNumber = input.next();

            isUserAuthenticated = database.authenticateAccount(accountNumber, pinNumber);

            if (!isUserAuthenticated) {
                database.accountMap.get(accountNumber).incrementAttempts();
            } else {
                database.accountMap.get(accountNumber).resetAttempts();
                promptForTransactionMenu();
            }
        }

        System.out.println("Too many attempts. Account locked. Please contact support.");
        isAccountAuthenticated = false;
    }

    public void promptForTransactionMenu() {
        // TODO: give enum options to user and wait for input
    }

    public void promptForDepositScreen() {
        // TODO: give options of how much to deposit and wait for input
    }

    public void promptForWithdrawlDenominations() {
        // TODO: give options of how much to withdraw and wait for input
    }
}
