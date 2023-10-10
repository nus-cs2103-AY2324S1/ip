package duke;

/**
 * Custom exception class for Duke, a chatbot application.
 * This exception is used to handle and propagate errors specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMessage The error message associated with this exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
