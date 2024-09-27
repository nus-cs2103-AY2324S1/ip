package chad.exception;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class EventException extends CommandException {
    public EventException() {
        super("event DESCRIPTION /from: YYYY-MM-DDTHH:MM /to: YYYY-MM-DDTHH:MM");
    }
}
