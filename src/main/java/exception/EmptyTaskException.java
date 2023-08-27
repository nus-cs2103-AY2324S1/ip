package exception;

public class EmptyTaskException extends MilException {
    public EmptyTaskException() {
        super("☹ Oopsie! You did not include any task description.");
    }
}
