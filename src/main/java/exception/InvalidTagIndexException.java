package exception;

public class InvalidTagIndexException extends MilException {
    public InvalidTagIndexException() {
        super("The index you input does not match any tag");
    }
}
