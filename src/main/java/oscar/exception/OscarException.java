package oscar.exception;

/**
 * Class to handle checked exceptions due to incorrect user input
 */
public class OscarException extends Exception {
    /**
     * Instantiates a custom exception with error message.
     *
     * @param errorMessage Informs the user with the exact issue.
     */
    public OscarException(String errorMessage) {
        super(errorMessage);
    }
}
