import java.util.ArrayList;
import java.util.List;

public class AccountHolder {
    private String firstName;
    private String lastName;
    private List<Account> accounts;

    public AccountHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        accounts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
