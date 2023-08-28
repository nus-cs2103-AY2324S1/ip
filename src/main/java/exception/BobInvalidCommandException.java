package exception;

public class BobInvalidCommandException extends BobException {
    public BobInvalidCommandException() {
        super("Invalid command format chosen");
    }

    public BobInvalidCommandException(String message) {
        super(message);
    }
}
