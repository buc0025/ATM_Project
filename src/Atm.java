import java.util.Scanner;

public class Atm {
    private boolean isUserAuthenticated;
    private boolean accountExists;
    private Database database;
    private Scanner input;
    private String accountNumber;
    private AtmScreens currentScreen;

    enum AtmScreens {
        LOGIN,
        TRANSACTION,
        CHECK_BALANCE,
        MAKE_DEPOSIT,
        MAKE_WITHDRAWAL;
    }

    public Atm() {
        currentScreen = AtmScreens.LOGIN;
        isUserAuthenticated = false;
        accountExists = false;
        database = new Database();
        input = new Scanner(System.in);
        accountNumber = "";
    }

    public void run() {
        while (true) {
            switch (currentScreen) {
                case LOGIN:
                    authenticateAccount();
                    break;
                case TRANSACTION:
                    promptForTransactionMenu();
                    break;
                case CHECK_BALANCE:
                    promptForCheckBalanceScreen();
                    break;
                case MAKE_DEPOSIT:
                    promptForDepositScreen();
                    break;
                case MAKE_WITHDRAWAL:
                    promptForWithdrawlDenominations();
                    break;
                default:
                    System.out.println("Wrong screen " + currentScreen);
                    throw new IllegalStateException("Incorrect screen path");
            }
        }
    }

    /**
     * This method checks to see if the database contains the account number the user inputs.
     * If the database contains the account number, it will check to see if the account is locked.
     * If the account is locked, accountExists is set to false and ATM will ask user to enter
     * another account number. If the database doesn't contain the user's inputted account number, it will
     * tell them it's an invalid account number and to try another input. Lastly, if the database contains
     * the account number and the account is not locked, the user will be asked to input their pin number.
     * The user is given 3 attempts before the account they are trying to access is locked.
     */
    public void authenticateAccount() {
        System.out.println("Please enter account number:");
        String acctNumber = input.next();

        accountExists = database.verifyAccountNumber(acctNumber);

        if (accountExists) {
            accountNumber = acctNumber;
            if (database.isAccountLocked(accountNumber)) {
                System.out.println("Account is locked. Please contact support.");
                accountExists = false;
            } else { // Valid account and is not locked
                System.out.println("Please enter pin number:");
                String pinNumber = input.next();

                if (database.authenticateAccount(accountNumber, pinNumber)) { // Account number and pin matches
                    database.accountMap.get(accountNumber).resetAttempts();
                    currentScreen = AtmScreens.TRANSACTION;
                    accountExists = true;
                } else { // Pin number does not match account number
                    accountExists = false;
                    database.accountMap.get(accountNumber).incrementAttempts();
                    if (database.accountMap.get(accountNumber).isAccountLocked()) {
                        System.out.println("Too many attempts. Account locked. Please contact support.");
                        currentScreen = AtmScreens.LOGIN;
                    } else {
                        System.out.println("Incorrect login. Please try again.");
                        currentScreen = AtmScreens.LOGIN;
                    }
                }
            }
        } else {
            System.out.println("Invalid account number. Please try again.");
        }
    }

    public void promptForTransactionMenu() {
        System.out.println("Press 1 to check acount balance \n" +
                "Press 2 to make a deposit \n" +
                "Press 3 to make a withdrawal \n" +
                "Press 4 to exit");

        int selection = input.nextInt();

        if (selection == 1) {
            currentScreen = AtmScreens.CHECK_BALANCE;
            promptForCheckBalanceScreen();
        } else if (selection == 2) {
            currentScreen = AtmScreens.MAKE_DEPOSIT;
            promptForDepositScreen();
        } else if (selection == 3) {
            currentScreen = AtmScreens.MAKE_WITHDRAWAL;
            promptForWithdrawlDenominations();
        } else {
            currentScreen = AtmScreens.LOGIN;
            authenticateAccount();
        }
    }

    public void promptForCheckBalanceScreen() {
        System.out.println("Your current account balance is: " + database.checkBalance(accountNumber) +
                "\n\nPress 1 to go back to main menu \n" +
                "Press 2 to exit");

        int selection = input.nextInt();

        if (selection == 1) {
            currentScreen = AtmScreens.TRANSACTION;
            promptForTransactionMenu();
        } else {
            currentScreen = AtmScreens.LOGIN;
            authenticateAccount();
        }
    }

    public void promptForDepositScreen() {
        System.out.println("Please enter deposit amount: ");

        double depositAmount = input.nextInt();

        database.deposit(accountNumber, depositAmount);

        System.out.println("Press 1 to make another deposit \n" +
                "Press 2 to go back to main menu \n" +
                "Press 3 to exit");

        int selection = input.nextInt();

        if (selection == 1) {
            currentScreen = AtmScreens.MAKE_DEPOSIT;
            promptForDepositScreen();
        } else if (selection == 2){
            currentScreen = AtmScreens.TRANSACTION;
            promptForTransactionMenu();
        } else {
            currentScreen = AtmScreens.LOGIN;
            authenticateAccount();
        }

    }

    public void promptForWithdrawlDenominations() {
        // TODO: give options of how much to withdraw and wait for input
        System.out.println("Press 1 to withdraw $20 \n" +
                "Press 2 to withdraw $40 \n" +
                "Press 3 to withdraw $60 \n" +
                "Press 4 to withdraw $80 \n" +
                "Press 5 to withdraw $100 \n" +
                "Press 6 to withdraw $200 \n" +
                "Press 7 to choose withdrawal amount \n" +
                "Press 8 to go back to main menu");

        int selection = input.nextInt();

        switch (selection) {
            case 1:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                database.withdraw(accountNumber, 20);
                promptAfterWithdrawal();
                break;
            case 2:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                database.withdraw(accountNumber, 40);
                promptAfterWithdrawal();
                break;
            case 3:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                database.withdraw(accountNumber, 60);
                promptAfterWithdrawal();
                break;
            case 4:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                database.withdraw(accountNumber, 80);
                promptAfterWithdrawal();
                break;
            case 5:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                database.withdraw(accountNumber, 100);
                promptAfterWithdrawal();
                break;
            case 6:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                database.withdraw(accountNumber, 200);
                promptAfterWithdrawal();
                break;
            case 7:
                currentScreen = AtmScreens.MAKE_WITHDRAWAL;
                promptCustomWithdrawal();
                break;
            case 8:
                currentScreen = AtmScreens.TRANSACTION;
                promptForTransactionMenu();
                break;
        }
    }

    public void promptCustomWithdrawal() {
        System.out.println("Withdrawal amount must be denominations of $20 \n" + "Please enter withdrawal amount: ");

        double amount = input.nextInt();

        currentScreen = AtmScreens.MAKE_WITHDRAWAL;

        if (amount % 20 != 0) {
            promptCustomWithdrawal();
        } else {
            database.withdraw(accountNumber, amount);
            promptAfterWithdrawal();
        }
    }

    public void promptAfterWithdrawal() {
        System.out.println("Press 1 to make another withdrawal \n" +
                "Press 2 to go back to main menu \n" +
                "Press 3 to exit");

        int selection = input.nextInt();

        if (selection == 1) {
            currentScreen = AtmScreens.MAKE_WITHDRAWAL;
            promptForWithdrawlDenominations();
        } else if (selection == 2){
            currentScreen = AtmScreens.TRANSACTION;
            promptForTransactionMenu();
        } else {
            currentScreen = AtmScreens.LOGIN;
            authenticateAccount();
        }
    }
}
