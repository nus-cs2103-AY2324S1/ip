package duke;

/**
 * The `DukeException` class is a custom exception that extends `RuntimeException`.
 * It is used to represent exceptions specific to the Duke chatbot application.
 * Each `DukeException` instance contains an error message and an error code for
 * identifying and handling different types of exceptions.
 */
public class DukeException extends RuntimeException {
    /**
     * The error code associated with the exception.
     */
    private final String errorCode;

    /**
     * Constructs a new `DukeException` with the specified error message and error code.
     *
     * @param errorMessage The error message describing the exception.
     * @param errorCode    The error code associated with the exception.
     */
    public DukeException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code associated with this exception.
     *
     * @return The error code as a String.
     */
    public String getErrorCode() {
        return errorCode;
    }
}
