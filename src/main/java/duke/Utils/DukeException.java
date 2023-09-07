package duke.Utils;

/**
 * The DukeException class is a custom runtime exception class for handling exceptions
 * specific to the Duke application.
 * It extends the RuntimeException class and includes a custom error message.
 */
public class DukeException extends RuntimeException {

    private String errorMessage;

    /**
     * Constructs a new DukeException with a custom error message.
     *
     * @param errorMessage The custom error message to be associated with the exception.
     */
    protected DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a string representation of the DukeException, including an error message.
     *
     * @return A string containing the error message preceded by "OOPS!!!".
     */
    @Override
    public String toString() {
        return "OOPS!!! " + errorMessage;
    }
}