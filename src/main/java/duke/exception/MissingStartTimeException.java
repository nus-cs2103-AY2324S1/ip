package duke.exception;

/**
 * A class that represents all exceptions thrown by the program when user wants to add an Event
 * object but did not input a start date
 */
public class MissingStartTimeException extends MissingInformationException {
    /**
     * Constructor for exception
     */
    public MissingStartTimeException() {
        super("☹ OOPS!!! Start time is required for task to be created. Denote start time with a /from.");
    }
}
