package pau.exception;

/**
 * Exception for an invalid end date when creating a deadline task.
 */
public class DeadlineNoEndException extends Exception {
    /**
     * Constructs a DeadlineNoEndException with an error message.
     */
    public DeadlineNoEndException(String msg) {
        super(msg);
    }
}
