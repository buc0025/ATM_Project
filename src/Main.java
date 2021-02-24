public class Main {

    public static void main(String[] args) {
        SystemInput systemInput = new SystemInput();
        Atm atm = new Atm(systemInput);
        atm.displayAccountsTest();
        atm.run();
    }
}
