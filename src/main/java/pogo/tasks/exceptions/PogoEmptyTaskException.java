package pogo.tasks.exceptions;

public class PogoEmptyTaskException extends PogoException {
    public PogoEmptyTaskException() {
        super("pogo.tasks.Task description cannot be empty");
    }
}
