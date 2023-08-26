public class RobertException extends Exception {
    private final String errorMessage;

    public RobertException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
        this.errorMessage = "OOPS!!! " + errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }
}
