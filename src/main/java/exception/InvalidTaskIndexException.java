package exception;

public class InvalidTaskIndexException extends MilException {
    public InvalidTaskIndexException() {
        super("Oopsie! The index you entered does not match any task.");
    }
}
