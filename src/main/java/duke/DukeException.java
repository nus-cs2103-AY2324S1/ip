package duke;

/**
 * Encapsulates some exceptions that are specific to adding tasks on the Duke bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a new Duke Exception.
     *
     * @param message message to be displayed to the user.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
