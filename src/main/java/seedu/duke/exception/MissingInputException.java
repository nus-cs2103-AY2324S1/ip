package seedu.duke.exception;

/**
 * Represents an MissingInputException that is thrown when the user enters a valid command with missing inputs.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class MissingInputException extends Exception {

    /**
     * Constructs a new MissingInputException with an error message.
     *
     * @param message The error message describing the missing input.
     */
    public MissingInputException(String message) {
        super(message);
    }
}