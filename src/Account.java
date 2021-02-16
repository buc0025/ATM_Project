public class Account {
    private AccountHolder user;
    private String accountType;
    private String accountNumber;
    private String pinNumber;
    private double accountBalance;
    private int attempts;

    public Account(AccountHolder user, String accountType, String accountNumber, String pinNumber, double accountBalance) {
        this.user = user;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.accountBalance = accountBalance;
        attempts = 0;
    }

    public String getUserName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void debitAccountBalance(double transactionAmount) {
        accountBalance = accountBalance + transactionAmount;
        System.out.println("You have deposited " + transactionAmount +
                "\nYour account balance is: " + accountBalance + "\n");
    }

    public void creditAccountBalance(double transactionAmount) {
        if (transactionAmount > accountBalance || accountBalance < 20) {
            System.out.println("Your account has insufficient funds for withdrawal.\n" +
                    "Your available balance is: " + accountBalance + "\n");
        } else {
            accountBalance = accountBalance - transactionAmount;
            System.out.println("You have withdrawn " + transactionAmount +
                    "\nYour account balance is: " + accountBalance + "\n");
        }
    }

    public boolean isAccountLocked() {
        return attempts >= 3;
    }

    public int getAttempts() {
        return attempts;
    }

    public void resetAttempts() {
        attempts = 0;
    }

    public void incrementAttempts() {
        attempts++;
    }
}
