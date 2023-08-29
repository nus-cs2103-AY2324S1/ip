package pogo.tasks.exceptions;

public class PogoInvalidTaskException extends PogoException {
    public PogoInvalidTaskException() {
        super("Invalid task type");
    }

    public PogoInvalidTaskException(String message) {
        super(message);
    }
}
