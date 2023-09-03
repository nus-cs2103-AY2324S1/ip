package duke.exception;
/**
 * Error when there is no file found.
 */
public class NoFileException extends DukeException {
    /**
     * Constructor for a NiFileException.
     * @param errorMessage message to be displayed when error is encountered
     */
    public NoFileException(String errorMessage) {
        super(errorMessage);
    }
}
