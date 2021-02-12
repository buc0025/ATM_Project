public class Main {

    public static void main(String[] args) {
//        Account stan1 = new Account("savings", "1", "11", 100, false, 0);
//        Account stan2 = new Account("savings", "1", "11", 100, false, 0);
//        AccountHolder stanley = new AccountHolder("Stanley", "Yang");
//        stanley.addAccount(stan1);
//        stanley.addAccount(stan2);
//        System.out.println(stanley.getAccounts());

        Database database = new Database();
        System.out.println(database.accountMap.get("1").getPinNumber());
        System.out.println(database.accountMap.get("1").getUserName());
        System.out.println(database.accountMap.get("1").getAccountBalance());
        database.deposit("1", 200);
        System.out.println(database.accountMap.get("1").getAccountBalance());
        database.sufficientFunds("1", 150);
        System.out.println(database.accountMap.get("1").getAccountBalance());
        database.sufficientFunds("1", 170);
        System.out.println(database.accountMap.get("1").getAccountBalance());

    }
}
