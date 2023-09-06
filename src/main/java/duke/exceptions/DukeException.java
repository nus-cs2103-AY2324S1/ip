package duke.exceptions;

public class DukeException extends Exception {
    /**
     * Throws an exception for the Duke program.
     *
     * @param message information about the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}