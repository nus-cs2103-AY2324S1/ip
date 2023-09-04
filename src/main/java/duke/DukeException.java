package duke;

/**
 * Signals an error caused by invalid inputs from user
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
