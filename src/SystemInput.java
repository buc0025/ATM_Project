import java.util.Scanner;

public class SystemInput implements UserInputInterface {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String getString() {
        return scanner.next();
    }

    @Override
    public int getInt() {
        return scanner.nextInt();
    }

    @Override
    public double getDouble() {
        return scanner.nextDouble();
    }

}
