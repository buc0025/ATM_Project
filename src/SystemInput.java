public class SystemInput implements UserInputInterface {
    TestInput testInput = new TestInput();

    @Override
    public String getString() {
        return testInput.getString();
    }

    @Override
    public int getInt() {
        return testInput.getInt();
    }
}
