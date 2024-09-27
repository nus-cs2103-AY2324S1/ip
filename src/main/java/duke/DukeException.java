package duke;

/**
 * Represents an error when running the program.
 */
public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
