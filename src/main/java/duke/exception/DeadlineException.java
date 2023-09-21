package duke.task;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class DeadlineException extends TaskException {

    public DeadlineException() {
        super("Valid Format: deadline (description) /by (YYYY-MM-DD)\n");
    }

}
