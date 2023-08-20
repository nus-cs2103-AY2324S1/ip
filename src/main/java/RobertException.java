public class RobertException extends Exception {
    private final String errorMessage;

    public RobertException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "OOPS!!! " + errorMessage;
    }
}
