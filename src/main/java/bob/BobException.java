package bob;

public class BobException extends Exception {
    protected String message;

    /**
     * Constructor for the BobException class
     *
     * @param message the reason for the exception
     */
    public BobException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the string representation of this BobException, showing the reason for this exception
     *
     * @return the string representation of the reason for the exception
     */
    @Override
    public String toString() {
        return message;
    }
}
