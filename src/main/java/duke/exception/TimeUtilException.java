package duke.exception;

/**
 * Represents an exception thrown when there's an error related to time utility functions in Duke.
 */
public class TimeUtilException extends DukeException {
    public TimeUtilException(String message) {
        super(String.format("%s %s", "[TimeUtil]", message));
    }
}
