package duke;

/**
 * Custom exception class for Duke application.
 */
public class LukeException extends Exception {
    /**
     * Constructs a new LukeException with the specified error message.
     *
     * @param errorMessage The error message associated with the exception
     */
    LukeException(String errorMessage) {
        super(errorMessage);
    }
}
