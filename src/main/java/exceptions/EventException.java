package exceptions;

/**
 * The exception class specific for Event tasks.
 *
 * @author Gallen Ong
 */
public class EventException extends RuntimeException {
    /**
     * Initialises an invalid format exception for Event tasks.
     */
    public EventException() {
        super("Invalid format for Event task. Please adhere to the following:\n" +
                "event (task) /from (start) /to (end)");
    }
}
