import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public Map<String, Account> accountMap;

    public Database() {
        accountMap = new HashMap<>();
        AccountHolder accountHolder1 = new AccountHolder("Stanley", "Yang");
        Account account1 = new Account(/*accountHolder*/accountHolder1,/*accountType*/"savings", /*accountNumber*/"1",
                /*pinNumber*/"11", /*accountBalance*/100, /*lockedAccount*/false, /*attempts*/0);
//        Account account2 = new Account("checking", "2", "22", 100, false, 0);
//        Account account3 = new Account("savings", "3", "33", 100, false, 0);

        accountHolder1.addAccount(account1);
        accountMap.put(account1.getAccountNumber(), account1);


    }

    public boolean verifyAccountNumber(String accountNumber) {
        if (accountMap.containsKey(accountNumber)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean authenticateAccount(String accountNumber, String pinNumber) {
        if (accountMap.get(accountNumber).getPinNumber().equals(pinNumber)) {
            return true;
        } else {
            return false;
        }
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

    public double withdraw(String accountNumber, double transactionAmount) {
        accountMap.get(accountNumber).setAccountBalance(checkBalance(accountNumber) - transactionAmount);
        return accountMap.get(accountNumber).getAccountBalance();
    }

    public double deposit(String accountNumber, double transactionAmount) {
        accountMap.get(accountNumber).setAccountBalance(checkBalance(accountNumber) + transactionAmount);
//        return accountMap.get(accountNumber).getAccountBalance() + transactionAmount;
        return accountMap.get(accountNumber).getAccountBalance();
    }

    public boolean lockedAccountStatus(String accountNumber) {
        return accountMap.get(accountNumber).isAccountLocked();
    }
}
