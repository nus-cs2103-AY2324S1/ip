package duke;

/**
 * This class represents a subclass of runtime exception
 * specifically for the Duke chatBot.
 */
public class DukeException extends RuntimeException {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }
}
