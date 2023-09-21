package duke.task;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class EventException extends TaskException {

    public EventException() {
        super("event (description) /from (YYYY-MM-DD) /to (YYYY-MM-DD)");
    }
}
