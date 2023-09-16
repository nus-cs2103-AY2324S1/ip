package exception;

public class NoTagIndexException extends MilException {
    public NoTagIndexException() {
        super("Oopsie! You did not include tag description :(");
    }
}
