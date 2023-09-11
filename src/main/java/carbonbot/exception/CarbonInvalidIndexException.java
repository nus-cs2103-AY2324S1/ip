package carbonbot.exception;

/**
 * Thrown to indicate an invalid task index was supplied.
 */
public class CarbonInvalidIndexException extends CarbonException {

    /**
     * Constructs a CarbonInvalidIndexException.
     */
    public CarbonInvalidIndexException() {
        super("The task list does not contain the given index. "
                + "Enter a valid task index by checking the task list.");
    }
}
