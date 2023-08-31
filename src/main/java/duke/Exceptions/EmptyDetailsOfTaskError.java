package duke.exceptions;

/**
 * Subclass of dukeException
 */
public class EmptyDetailsOfTaskError extends DukeException {
    /**
     * Constructor of the EmptyDetailsOfTaskError
     * @param message is the message given by the bot when encountering
     *                the error in which the task do not have
     *                any details
     */
    public EmptyDetailsOfTaskError(String message) {
        super(message);
    }
}
