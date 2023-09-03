package skye.data.exception;

/**
 * Represents the exception class specifically for the program.
 */
public class DukeException extends Exception {

    /**
     * Initializes the exception class by providing an error message.
     *
     * @param errorMessage Error message to be shown to the user
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Initializes the exception class by providing the exception type.
     *
     * @param exceptionType The type of exception which is represented as an enum
     */
    public DukeException(DukeExceptionType exceptionType) {
        super(exceptionType.getMessage());
    }
}