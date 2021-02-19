import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class AccountHolder {
    private String firstName;
    private String lastName;
    private UUID uuid;
    private List<Account> accounts;

    AccountHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        accounts = new ArrayList<>();
        uuid = UUID.randomUUID();
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    void addAccount(Account account) {
        accounts.add(account);
    }

    UUID getUuid() {
        return uuid;
    }

    List<Account> getAccounts() {
        return accounts;
    }
}
