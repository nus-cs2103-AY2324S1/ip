package exceptions;

/**
 * The exception class specific for Todo tasks.
 *
 * @author Gallen Ong
 */
public class TodoException extends RuntimeException {
    /**
     * Initialises an invalid format exception for Todo tasks.
     */
    public TodoException() {
        super("Invalid format for Todo task. Kindly adhere to the following:\n" +
              "todo (task)\n" + "eg. todo read book");
    }
}
