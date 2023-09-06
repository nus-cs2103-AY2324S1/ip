package duke;

/**
 * Represents an exception specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object.
     *
     * @param message The message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}
