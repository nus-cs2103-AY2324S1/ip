package duke.exception;
/**
 * Exceptions that will be thrown whilst executing duke program.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a DukeException.
     * @param errorMessage message to be shown when error is thrown
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
