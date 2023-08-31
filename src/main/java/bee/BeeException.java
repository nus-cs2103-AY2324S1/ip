package bee;

public class BeeException extends Exception {
    public BeeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "    _  _\n" +
                "   | )/ )\n" +
                "\\\\ |//,' __\n" +
                "(\")(_)-\"()))=-\n" +
                "   (\\\\ " + super.getMessage();
    }
}