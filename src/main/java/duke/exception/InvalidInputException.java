package duke.exception;

/**
 * Represents an exception that occurs when the user input is invalid or not recognized.
 * This exception is thrown when the user provides an input that does not match any of the valid instructions.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs an InvalidInputException with a custom error message.
     *
     * @param message The error message indicating that the input provided is invalid.
     */
    public InvalidInputException(String message) {
        super(message + ":" + "\n\tAccio error! I don't know what the input means D: " +
                "Please enter valid inputs. Type 'help' to see the list of valid inputs! ");
    }

}
