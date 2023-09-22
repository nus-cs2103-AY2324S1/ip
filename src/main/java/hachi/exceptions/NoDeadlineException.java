package hachi.exceptions;

/**
 * Exception thrown when no deadline is supplied to the "deadline" command
 */
public class NoDeadlineException extends HachiException {
    /**
     * Constructor for the NoDeadlineException class
     */
    public NoDeadlineException() {
        super("☹ OOPS!!! Missing deadline, please input using this format:"
                + " \"deadline <description> /by <deadline>\"");
    }
}
