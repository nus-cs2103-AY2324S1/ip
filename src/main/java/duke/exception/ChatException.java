package duke.exception;

/**
 * Custom exception class for the program.
 */
public class ChatException extends Exception {
    /**
     * Custom exception to be thrown for the program.
     * @param errorMessage Description of the error encountered.
     */
    public ChatException(String errorMessage) {
        super(errorMessage);
    }
}
