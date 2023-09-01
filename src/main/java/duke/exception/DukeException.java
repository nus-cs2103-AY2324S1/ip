package duke.exception;

/**
 * A class that represents all exceptions thrown by the program (other than FileNotFoundException)
 */
public class DukeException extends Exception {
    /**
     * Constructor for exception
     * @param message Error message to be printed onto the console
     */
    public DukeException(String message) {
        super(message);
    }
}
