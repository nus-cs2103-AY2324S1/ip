package duke;

/**
 * Specialized exception for Duke project.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DukeException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
