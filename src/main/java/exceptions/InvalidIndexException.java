package exceptions;

public class InvalidIndexException extends ThorndikeException {

    public InvalidIndexException() {
        super("The index given is invalid");
    }
}
