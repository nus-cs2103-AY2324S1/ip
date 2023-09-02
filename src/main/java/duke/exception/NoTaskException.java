package duke.exception;
/**
 * Exception when there is no task given.
 */
public class NoTaskException extends DukeException{
    /**
     * Constructor of a NoTaskException.
     * @param errorMessage message to be displayed when error is encountered
     */
    public NoTaskException(String errorMessage) {
        super(errorMessage);
    }
}
