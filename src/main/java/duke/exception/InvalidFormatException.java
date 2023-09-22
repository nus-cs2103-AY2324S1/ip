package duke.exception;
/**
 * Exception when encountering invalid format.
 */
public class InvalidFormatException extends DukeException {
    /**
     * Constructor for InvalidFormatException.
     * @param errorMessage message to be displayed when encountering error
     */
    public InvalidFormatException(String errorMessage) {
        super(errorMessage);
    }
}
