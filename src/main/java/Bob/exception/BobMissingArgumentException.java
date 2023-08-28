package Bob.exception;

public class BobMissingArgumentException extends BobException{
    public BobMissingArgumentException() {
        super("You are missing an argument!");
    }
    public BobMissingArgumentException(String message) {
        super(message);
    }
}
