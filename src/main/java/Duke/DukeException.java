package Duke;

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