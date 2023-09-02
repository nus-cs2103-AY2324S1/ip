package exceptions;
/**
 * Custom exception class to handle cases where input has invalid index entered.
 */
public class InvalidIndexException extends Exception {

    /**
     * Public constructor for throwing InvalidIndexException
     * @param message string of text
     */
    public InvalidIndexException(String message) {
        super("Macho! The target you delete is out of the list or does not exist, please enter again macho!\n"
            + message);
    }
}
