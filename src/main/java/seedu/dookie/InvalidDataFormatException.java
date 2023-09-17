package seedu.dookie;

/**
 * Encapsulates the exception where the saved data in dookie.txt is not formatted correctly.
 */
public class InvalidDataFormatException extends DookieException{
    /**
     * Creates an InvalidDataFormatException.
     */
    public InvalidDataFormatException() {
        super("\u2639 OOPS!!! The saved data format is invalid!");
    }
}
