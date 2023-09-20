package seedu.duke.exceptions;

/**
 * MissingKeywordException
 */
public class MissingKeywordException extends Exception {
    private String message;

    /**
     * Exception for missing keyword in command
     * @param message error message
     */
    public MissingKeywordException(String message) {
        super("MissingKeywordException: " + message + "\n");
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + "\n";
    }
}
