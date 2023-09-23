package duke;

/**
 * DukeException encapsulates custom exceptions thrown
 * by the Duke project.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     * @param errorMessage Custom error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
