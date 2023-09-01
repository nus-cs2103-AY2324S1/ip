package duke.exceptions;

/**
 * Encapsulations of all exceptions thrown by the application.
 */
public class DukeException extends Exception {
    /**
     * Public constructor for DukeException
     * @param message the message describing the exception
     */
    public DukeException(String message) {
        super("OOPS!!! " + message);
    }

    /**
     * String representation of the Task object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}
