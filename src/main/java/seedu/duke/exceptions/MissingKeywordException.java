package seedu.duke.exceptions;

/**
 * MissingKeywordException
 */
public class MissingKeywordException extends Exception {
    private String message;

    public MissingKeywordException(String message) {
        super("MissingKeywordException: " + message + "\n");
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
