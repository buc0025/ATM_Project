import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public Map<String, Account> accountMap;

    public Database() {
        accountMap = new HashMap<>();
        AccountHolder accountHolder1 = new AccountHolder("Stanley", "Yang");
        Account account1 = new Account(/*accountHolder*/accountHolder1,/*accountType*/"savings", /*accountNumber*/"1",
                /*pinNumber*/"11", /*accountBalance*/100);
//        Account account2 = new Account("checking", "2", "22", 100, false, 0);
//        Account account3 = new Account("savings", "3", "33", 100, false, 0);

        accountHolder1.addAccount(account1);
        accountMap.put(account1.getAccountNumber(), account1);

    }

    public boolean verifyAccountNumber(String accountNumber) {
        return accountMap.containsKey(accountNumber);
    }

    public boolean authenticateAccount(String accountNumber, String pinNumber) {
        return accountMap.get(accountNumber).getPinNumber().equals(pinNumber);
    }

    public double checkBalance(String accountNumber) {
        return accountMap.get(accountNumber).getAccountBalance();
    }

    public void sufficientFunds(String accountNumber, double transactionAmount) {
        if (accountMap.get(accountNumber).getAccountBalance() < transactionAmount) {
            System.out.println("Not enough funds for withdrawal.");
        } else {
            withdraw(accountNumber, transactionAmount);
        }
    }

    public void withdraw(String accountNumber, double transactionAmount) {
        accountMap.get(accountNumber).creditAccountBalance(transactionAmount);
    }

    public void deposit(String accountNumber, double transactionAmount) {
        accountMap.get(accountNumber).debitAccountBalance(transactionAmount);
    }

    public boolean isAccountLocked(String accountNumber) {
        return accountMap.get(accountNumber).isAccountLocked();
    }
}
