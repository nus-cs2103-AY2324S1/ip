package duke.exception;

/**
 * A class that represents all exceptions thrown by the program when user wants to add
 * a Deadline object but did not provide a date to denote the deadline
 */
public class MissingDeadlineException extends MissingInformationException {
    /**
     * Constructor for exception
     */
    public MissingDeadlineException() {
        super("☹ OOPS!!! Deadline is required for task to be created. Denote deadline with a /by.");
    }
}
