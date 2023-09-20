package exception;

public class InvalidDeadlineException extends MilException {
    public InvalidDeadlineException() {
        super("Oopsie! Please add your deadline with the following format: \"deadline (description) /by (yyyy-mm-dd) \".");
    }
}
