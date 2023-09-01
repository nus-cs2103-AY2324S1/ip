package duke.exception;

/**
 * A class that represents all exceptions thrown by the program when user wants to add an Event
 * object but did not input an end date
 */
public class MissingEndTimeException extends MissingInformationException {
    /**
     * Constructor for exception
     */
    public MissingEndTimeException() {
        super("☹ OOPS!!! end time is required for task to be created. Denote end time with a /to.");
    }
}
