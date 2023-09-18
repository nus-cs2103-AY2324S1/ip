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
        super("Invalid format for Event task. Kindly adhere to the following:\n" +
              "event (task) /from (start in YYYY-MM-DD) /to (end in YYYY-MM-DD)\n" +
              "eg. event attend meeting /from 2023-09-21 /to 2023-09-22");
    }
}
