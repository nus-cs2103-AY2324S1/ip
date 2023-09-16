package exception;

public class EmptyTagException extends MilException {
    public EmptyTagException() {
        super("Oopsie! Cannot create an empty tag.");
    }
}
