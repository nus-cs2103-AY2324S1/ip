package exception;

public class UnknownCommandException extends ChattyException {
    public UnknownCommandException() {
        super("I don't know what that means. Please check that you have entered the correct instructions");
    }
}
