package duke.exception;

/**
 * if an input entered by user does not start with one of the command word
 */
public class UnknownCommandException extends ChattyException {
    public UnknownCommandException() {
        super("Sorry, I don't understand what that means.");
    }
}
