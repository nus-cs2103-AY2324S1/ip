package duke;

/**
 * Exception class for handling cases where there is no search parameter in Duke.
 */
public class NoSearchParameterException extends DukeException {
    /**
     * Constructs a new NoSearchParameterException with a default error message.
     */
    public NoSearchParameterException() {
        super("OOPS!!! No search parameter.");
    }

    /**
     * Returns a string representation of this NoSearchParameterException, including the error message.
     *
     * @return A string representation of the exception's detail message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
