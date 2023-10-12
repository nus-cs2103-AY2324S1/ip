package aj;

/**
 * WrongDescriptionException class for exceptions related to invalid task description from user input
 */
public class WrongDescriptionException extends AjException {
    WrongDescriptionException(String errMsg) {
        super(errMsg);
    }
}
