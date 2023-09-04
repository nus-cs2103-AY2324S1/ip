package duke.exception;

/**
 * InvalidDeadlineException inherits from BobiException.
 * An InvalidDeadlineException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class InvalidDeadlineException extends BobiException {
    /**
     * Constructor of an InvalidDeadlineException object.
     */
    public InvalidDeadlineException() {
        super("Oh no! It seems like you have indicated an invalid deadline :/ \n"
                + "Please follow this format:\n"
                + "deadline <task name> /by <yyyy-mm-dd> <HHmm>"
        );
    }
}
