package pogo.tasks.exceptions;

public class PogoInvalidTaskException extends PogoException {
    public PogoInvalidTaskException() {
        super("Invalid task type");
    }
}
