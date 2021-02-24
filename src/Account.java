class Account {
    private AccountHolder user;
    private String accountType;
    private String accountNumber;
    private String pinNumber;
    private double accountBalance;
    private int attempts;

    Account(AccountHolder user, String accountType, String accountNumber, String pinNumber, double accountBalance) {
        this.user = user;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.accountBalance = accountBalance;
        attempts = 0;
    }

    String getUserName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    String getAccountType() {
        return accountType;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    String getPinNumber() {
        return pinNumber;
    }

    double getAccountBalance() {
        return accountBalance;
    }

    void debitAccountBalance(double transactionAmount) {
        if (transactionAmount < 0) {
            System.out.println("Amount must be greater than $0.00");
        } else {
            accountBalance = accountBalance + transactionAmount;
            System.out.printf("You have deposited $%.2f%n%s%.2f%n", transactionAmount,
                    "Your account balance is: $", accountBalance);
        }
    }

    void creditAccountBalance(double transactionAmount) {
        if (transactionAmount < 20) {
            System.out.println("Amount must be at least $20");
        } else if (transactionAmount > accountBalance || accountBalance < 20) {
            System.out.println("Your account has insufficient funds for withdrawal.\n" +
                    "Your available balance is: " + accountBalance + "\n");
        } else if (transactionAmount % 20 != 0) {
            System.out.println("Amount must be in $20 denominations");
        } else {
            accountBalance = accountBalance - transactionAmount;
            System.out.printf("You have withdrawn $%.2f%n%s%.2f%n" , transactionAmount,
                    "Your account balance is: $", accountBalance);
        }
    }

    boolean isAccountLocked() {
        return attempts >= 3;
    }

    void resetAttempts() {
        attempts = 0;
    }

    void incrementAttempts() {
        attempts++;
    }
}
