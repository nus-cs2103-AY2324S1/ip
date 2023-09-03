package duke.exception;

/**
 * InvalidEventException inherits from BobiException.
 * An InvalidEventException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class InvalidEventException extends BobiException {
    /**
     * Constructor of an InvalidEventException object.
     */
    public InvalidEventException() {
        super("Oh no! It seems like you have indicated an invalid event :/ \n"
                + "Please follow this format:\n"
                + "event <task name> /from <yyyy-mm-dd> <HHmm> /to <yyyy-mm-dd> <HHmm>"
        );
    }
}
