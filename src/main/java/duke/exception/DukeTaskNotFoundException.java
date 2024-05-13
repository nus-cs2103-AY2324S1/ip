package duke.exception;

/**
 * Represents an exception that occurs when a task cannot be found.
 */
public class DukeTaskNotFoundException extends DukeException {
    /**
     * Constructs a DukeTaskNotFoundException.
     */
    public DukeTaskNotFoundException() {
        super("I can't find that task!\n");
    }
}
