package duke.exception;
/**
 * Exception when encountering invalid arguments.
 */
public class InvalidArgumentException extends DukeException{
    /**
     * Invalid argument exception that throws error when invalid
     * arguments are inputted with a function.
     * @param errorMessage message describing the error
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
