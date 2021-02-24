import java.util.InputMismatchException;
import java.util.Scanner;

class Atm {
    private boolean accountExists;
    private Database database;
    private Scanner input;
    private String accountNumber;
    private AtmScreens currentScreen;
    private UserInputInterface userInputInterface;

    enum AtmScreens {
        LOGIN,
        TRANSACTION,
        CHECK_BALANCE,
        MAKE_DEPOSIT,
        OPTIONS_AFTER_DEPOSIT,
        WITHDRAWAL_OPTIONS,
        CUSTOM_WITHDRAWAL,
        OPTIONS_AFTER_WITHDRAWAL;
    }

    Atm(UserInputInterface userInputInterface) {
        currentScreen = AtmScreens.LOGIN;
        accountExists = false;
        database = new Database();
        input = new Scanner(System.in);
        accountNumber = "";
        this.userInputInterface = userInputInterface;
    }

    /**
     * Each screen will prompt user for a response
     */
    void run() {
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
                case OPTIONS_AFTER_DEPOSIT:
                    promptAfterDeposit();
                    break;
                case WITHDRAWAL_OPTIONS:
                    promptForWithdrawalDenominations();
                    break;
                case CUSTOM_WITHDRAWAL:
                    promptCustomWithdrawal();
                    break;
                case OPTIONS_AFTER_WITHDRAWAL:
                    promptAfterWithdrawal();
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
    void authenticateAccount() {
        System.out.println("Please enter account number:");
        String acctNumber = input.next();
        accountExists = database.verifyAccountNumber(acctNumber);

        if (!accountExists) {
            System.out.println("Invalid account number. Please try again.");
            return;
        }

        if (database.isAccountLocked(acctNumber)) {
            System.out.println("Account is locked. Please contact support.");
            return;
        }

        accountNumber = acctNumber;
        System.out.println("Please enter pin number:");
        String pinNumber = input.next();
        database.accountMap.get(accountNumber).incrementAttempts(); // Attempts at logging into account is incremented

        if (database.authenticateAccount(accountNumber, pinNumber)) { // Account number and pin matches
            database.accountMap.get(accountNumber).resetAttempts();
            currentScreen = AtmScreens.TRANSACTION;
            accountExists = true;
        }

        if (!database.authenticateAccount(accountNumber, pinNumber)) { // Account number and pin doesn't match
            if (database.accountMap.get(accountNumber).isAccountLocked()) { // 3 incorrect attempts at logging in
                accountExists = false;
                currentScreen = AtmScreens.LOGIN;
                System.out.println("Too many attempts. Account locked. Please contact support");
            } else {
                accountExists = false;
                System.out.println("Incorrect login. Please try again.");
                currentScreen = AtmScreens.LOGIN;
            }
        }
    }

    /**
     * User is asked whether they want to check balance, make a deposit, withdraw money, or exit.
     */
    void promptForTransactionMenu() {
        System.out.println("Press 1 to check account balance \n" +
                "Press 2 to make a deposit \n" +
                "Press 3 to make a withdrawal \n" +
                "Press 4 to exit");

        try {
            int selection = input.nextInt();
            if (selection == 1) {
                currentScreen = AtmScreens.CHECK_BALANCE;
            } else if (selection == 2) {
                currentScreen = AtmScreens.MAKE_DEPOSIT;
            } else if (selection == 3) {
                currentScreen = AtmScreens.WITHDRAWAL_OPTIONS;
            } else if (selection == 4) {
                System.out.println("You have been logged out.");
                currentScreen = AtmScreens.LOGIN;
            } else {
                System.out.println("Invalid input. Please try again.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            input.next();
        }
    }

    /**
     * Displays account balance and given the option to go back to main menu or exit.
     */
    void promptForCheckBalanceScreen() {
        System.out.printf("Your current account balance is: $%.2f%n%s%n%s%n", database.checkBalance(accountNumber),
                "Press 1 to go back to main main", "Press 2 to exit");

        try {
            int selection = input.nextInt();
            if (selection == 1) {
                currentScreen = AtmScreens.TRANSACTION;
            } else if (selection == 2) {
                System.out.println("You have been logged out.");
                currentScreen = AtmScreens.LOGIN;
            } else {
                System.out.println("Invalid input. Please try again.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            input.next();
        }
    }

    /**
     * Asks user for the amount the would like to deposit and tells them the new balance afterwards
     */
    void promptForDepositScreen() {
        System.out.println("Please enter deposit amount: ");

        try {
            double depositAmount = input.nextDouble();
            database.deposit(accountNumber, depositAmount);
            if (depositAmount < 0) {
                return;
            }
            currentScreen = AtmScreens.OPTIONS_AFTER_DEPOSIT;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            input.next();
            return;
        }
    }

    /**
     * Asks user what they want to do after they have made a deposit
     */
    void promptAfterDeposit() {
        System.out.println("Press 1 to make another deposit \n" +
                "Press 2 to go back to main menu \n" +
                "Press 3 to exit");

        try {
            int selection = input.nextInt();

            if (selection == 1) {
                currentScreen = AtmScreens.MAKE_DEPOSIT;
            } else if (selection == 2){
                currentScreen = AtmScreens.TRANSACTION;
            } else if (selection == 3) {
                System.out.println("You have been logged out.");
                currentScreen = AtmScreens.LOGIN;
            } else {
                System.out.println("Invlid input. Please try again.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            input.next();
        }
    }

    /**
     * Gives user a few choices of how much to withdraw along with option to make a specific withdrawal amount and to
     * go back to previous screen. New account balance will be displayed after withdrawal.
     */
    void promptForWithdrawalDenominations() {
        System.out.println("Press 1 to withdraw $20 \n" +
                "Press 2 to withdraw $40 \n" +
                "Press 3 to withdraw $60 \n" +
                "Press 4 to withdraw $80 \n" +
                "Press 5 to withdraw $100 \n" +
                "Press 6 to withdraw $200 \n" +
                "Press 7 to choose withdrawal amount \n" +
                "Press 8 to go back to main menu");

        try {
            int selection = input.nextInt();

            switch (selection) {
                case 1:
                    currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                    database.withdraw(accountNumber, 20);
                    break;
                case 2:
                    currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                    database.withdraw(accountNumber, 40);
                    break;
                case 3:
                    currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                    database.withdraw(accountNumber, 60);
                    break;
                case 4:
                    currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                    database.withdraw(accountNumber, 80);
                    break;
                case 5:
                    currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                    database.withdraw(accountNumber, 100);
                    break;
                case 6:
                    currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                    database.withdraw(accountNumber, 200);
                    break;
                case 7:
                    currentScreen = AtmScreens.CUSTOM_WITHDRAWAL;
                    break;
                case 8:
                    currentScreen = AtmScreens.TRANSACTION;
                    break;
                default:
                    System.out.println("Invalid input. Please try again");
                    return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again");
            input.next();
        }
    }

    /**
     * Asks user to withdraw an amount that's not listed in the WITHDRAWAL_OPTIONS screen. Amount has to be in
     * denominations of $20
     */
    void promptCustomWithdrawal() {
        System.out.println("Withdrawal amount must be denominations of $20 \n" + "Please enter withdrawal amount: ");

        try {
            double amount = input.nextDouble();
            currentScreen = AtmScreens.WITHDRAWAL_OPTIONS;

            if (amount % 20 != 0) {
                currentScreen = AtmScreens.CUSTOM_WITHDRAWAL;
            } else {
                currentScreen = AtmScreens.OPTIONS_AFTER_WITHDRAWAL;
                database.withdraw(accountNumber, amount);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again");
            input.next();
        }
    }

    /**
     * Asks user what they want to do after they have made a withdrawal
     */
    void promptAfterWithdrawal() {
        System.out.println("Press 1 to make another withdrawal \n" +
                "Press 2 to go back to main menu \n" +
                "Press 3 to exit");

        try {
            int selection = input.nextInt();

            if (selection == 1) {
                currentScreen = AtmScreens.WITHDRAWAL_OPTIONS;
            } else if (selection == 2){
                currentScreen = AtmScreens.TRANSACTION;
            } else if (selection == 3) {
                System.out.println("You have been logged out.");
                currentScreen = AtmScreens.LOGIN;
            } else {
                System.out.println("Invlid input. Please try again.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again");
            input.next();
        }
    }

    /**
     * Displays randomly generated account numbers to test before run() is called
     */
    void displayAccountsTest() {
//        System.out.println(database.accountNumbers);
        System.out.println(database.accountMap.keySet());
    }

    AtmScreens getCurrentScreen() {
        return currentScreen;
    }
}
