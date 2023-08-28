package Bob.exception;

public class BobInvalidTaskNumberException extends BobException {
    public BobInvalidTaskNumberException() {
        super("Index chosen for task is invalid");
    }

    public BobInvalidTaskNumberException(String message) {
        super(message);
    }
}
