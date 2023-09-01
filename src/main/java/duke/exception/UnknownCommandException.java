package duke.exception;

public class UnknownCommandException extends ChattyException {
    public UnknownCommandException() {
        super("Sorry, I don't understand what that means.");
    }
}
