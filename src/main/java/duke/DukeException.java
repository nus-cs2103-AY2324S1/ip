package duke;

/**
 * The exception class which encapsulates all error messages to be sent by WallE.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs an instance of the DukeException without a specified message.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructs an instance of the DukeException with a specified message.
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}
