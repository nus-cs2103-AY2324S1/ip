package exception;

public class EmptyTaskException extends MilException {
    public EmptyTaskException() {
        super("Oopsie! You did not include tag description :(");
    }
}
