package exceptions;

/**
 * The exception class specific for Deadline tasks.
 *
 * @author Gallen Ong
 */
public class DeadlineException extends RuntimeException{
    /**
     * Initialises an invalid format exception for Deadline tasks.
     */
    public DeadlineException() {
        super("Invalid format for Deadline task. Please adhere to the following:\n" +
                "deadline (task) /by (deadline)");
    }
}
