package exception;

public class EmptyFindQueryException extends MilException {
    public EmptyFindQueryException() {
        super("☹ Oopsie! You did not include any query.");
    }
}

