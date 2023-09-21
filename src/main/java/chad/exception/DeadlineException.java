package chad.exception;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class DeadlineException extends CommandException {

    public DeadlineException() {
        super("Valid Format: deadline DESCRIPTION /by: YYYY-MM-DDTHH:MM)\n");
    }

}
