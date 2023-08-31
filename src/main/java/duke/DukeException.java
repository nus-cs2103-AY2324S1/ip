package duke;

/**
 * Custom exception class for duke.Duke chatbot.
 * This exception is thrown when there is an issue or error encountered during chatbot operations.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new duke.DukeException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this duke.DukeException, which includes the error message.
     *
     * @return A string representation of the exception's detail message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
