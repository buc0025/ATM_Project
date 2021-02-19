import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Database {
    public Map<String, Account> accountMap;
    public Map<String, String> accountNumbers;

    public Database() {
        accountMap = new HashMap<>();
        accountNumbers = new HashMap<>();

        AccountHolder accountHolder1 = new AccountHolder("Stanley", "Yang");
        Account account1 = new Account(/*accountHolder*/accountHolder1,/*accountType*/"savings", /*accountNumber*/createAccountNumber(),
                /*pinNumber*/"11", /*accountBalance*/100);
        Account account2 = new Account(/*accountHolder*/accountHolder1,/*accountType*/"savings", /*accountNumber*/createAccountNumber(),
                /*pinNumber*/"22", /*accountBalance*/150);

        accountHolder1.addAccount(account2);
        accountHolder1.addAccount(account1);
        accountMap.put(account1.getAccountNumber(), account1);
        accountMap.put(account2.getAccountNumber(), account2);

        accountNumbers.put(account1.getAccountNumber() + " pin: " + account1.getPinNumber(), account1.getUserName());
        accountNumbers.put(account2.getAccountNumber(), account2.getUserName());

    }

    /**
     * Method checks to see if database contains account number inputted by user
     * @param accountNumber user's account number
     * @return boolean whether database contains account number
     */
    public boolean verifyAccountNumber(String accountNumber) {
        return accountMap.containsKey(accountNumber);
    }

    /**
     * Method is called after database is checked if it contains account number. It checks
     * to see if pin number matches account number
     * @param accountNumber user's account number
     * @param pinNumber user's pin number
     * @return boolean whether pin number matches account number
     */
    public boolean authenticateAccount(String accountNumber, String pinNumber) {
        return accountMap.get(accountNumber).getPinNumber().equals(pinNumber);
    }

    /**
     * Method checks user's balance after account has been authenticated
     * @param accountNumber user's account number
     * @return account balance
     */
    public double checkBalance(String accountNumber) {
        return accountMap.get(accountNumber).getAccountBalance();
    }

    /**
     * Method credits account balance if there's sufficient funds and if withdrawal amount is in
     * denominations of $20.
     * @param accountNumber user's account number
     * @param transactionAmount withdrawal amount
     */
    public void withdraw(String accountNumber, double transactionAmount) {
        accountMap.get(accountNumber).creditAccountBalance(transactionAmount);
    }

    /**
     * Method debits account balance and displays new account balance after deposit
     * @param accountNumber user's account number
     * @param transactionAmount deposit amount
     */
    public void deposit(String accountNumber, double transactionAmount) {
        accountMap.get(accountNumber).debitAccountBalance(transactionAmount);
    }

    /**
     * Method checks to see if account is locked after user enters 3 unsuccessful pin number attempts
     * @param accountNumber user's account number
     * @return boolean value if user is locked out of account
     */
    public boolean isAccountLocked(String accountNumber) {
        return accountMap.get(accountNumber).isAccountLocked();
    }

    /**
     * Method generates random 6 digit number that will be used as an account number
     * @return random number from 100,000 to 999,999
     */
    public String createAccountNumber() {
        Random random = new Random();
        int n = 100000 + random.nextInt(899999);
        return String.valueOf(n);
    }
}
