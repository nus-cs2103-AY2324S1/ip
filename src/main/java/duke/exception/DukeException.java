package duke.exception;

/**
 * The `DukeException` class represents an exception specific to the Duke application.
 */
public class DukeException extends Exception {

    public static final String WRONG_DATETIME_MESSAGE = "Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.";

    /**
     * Constructs a new `DukeException` with the specified error message.
     *
     * @param errorMsg The error message associated with the exception.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
