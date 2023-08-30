package exception;

public class InternalException extends DukeException {
    public InternalException(String message) {
        super("Internal exception: " + message);
    } 
}
