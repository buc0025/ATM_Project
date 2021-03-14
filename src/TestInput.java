public class TestInput implements UserInputInterface {

    public String[] stringInputs;
    public int stringsIndex;

    public int[] intInputs;
    public int intIndex;

    public double[] doubleInputs;
    public int doubleIndex;

    @Override
    public String getString() {
        return stringInputs[stringsIndex++];
    }

    @Override
    public int getInt() {
        return intInputs[intIndex++];
    }

    @Override
    public double getDouble() {
        return doubleInputs[doubleIndex++];
    }
}
