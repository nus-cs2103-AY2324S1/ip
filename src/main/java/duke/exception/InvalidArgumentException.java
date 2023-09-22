package duke.exception;
/**
 * Exception when encountering invalid arguments.
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Constructor for invalid argument exception.
     * @param errorMessage message describing the error
     */
    public InvalidArgumentException(String errorMessage) {
        super("Please enter " + errorMessage + " without any extra arguments or use a different keyword");
    }
}
