package Duke.Exceptions;

/**
 * Represents an exception that is thrown when an unknown time format is encountered.
 */
public class UnknownTimeException extends Exception {

    /**
     * Constructs an UnknownTimeException with a formatted error message.
     *
     * @param message The task description for which the time is unknown.
     */
    public UnknownTimeException(String message) {
        super(String.format("☹ OOPS!!! Sorry but what is that time? %s is missing it", message));
    }
}
