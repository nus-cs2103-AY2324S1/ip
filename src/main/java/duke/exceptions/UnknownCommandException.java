package duke.exceptions;

/**
 * Represents the exception thrown when the user inputs an unknown command.
 */
public class UnknownCommandException extends Exception {
    /**
     * Creates an UnknownCommandException object.
     * 
     * @param message The description of the exception.
     */
    public UnknownCommandException(String message) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n    You typed: " + message);
    }
}