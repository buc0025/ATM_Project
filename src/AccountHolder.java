import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountHolder {
    private String firstName;
    private String lastName;
    private UUID uuid;
    private List<Account> accounts;

    public AccountHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        accounts = new ArrayList<>();
        uuid = UUID.randomUUID();
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

    public UUID getUuid() {
        return uuid;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
