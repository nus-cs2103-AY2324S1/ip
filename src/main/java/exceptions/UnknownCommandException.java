package exceptions;

/**
 * Represents the exception thrown when the user inputs an unknown command.
 */
public class UnknownCommandException extends Exception{
    public UnknownCommandException(String message) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n    You typed: " + message);
    }
}
