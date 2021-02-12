public class Account {
    private AccountHolder user;
    private String accountType;
    private String accountNumber;
    private String pinNumber;
    private double accountBalance;
    private boolean lockedAccount;
    private int attempts;

    public Account(AccountHolder user, String accountType, String accountNumber, String pinNumber, double accountBalance, boolean lockedAccount, int attempts) {
        this.user = user;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.pinNumber = pinNumber;
        this.accountBalance = accountBalance;
        this.lockedAccount = lockedAccount;
        this.attempts = attempts;
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

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public boolean isAccountLocked() {
        return lockedAccount;
    }

    public void setLockedAccount(boolean lockedAccount) {
        this.lockedAccount = lockedAccount;
    }

    public int getAttempts() {
        return attempts;
    }

    public void resetAttempts() {

    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
