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
        super("Invalid format for Deadline task given. Kindly adhere to the following:\n" +
              "deadline (task) /by (deadline in YYYY-MM-DD)\n" +
              "eg. deadline return book /by 2023-09-21");
    }
}
