package duke;

/**
 * This class represents a subclass of runtime exception
 * specifically for the Duke chatBot.
 */
public class IrisException extends RuntimeException {
    public IrisException() {
        super();
    }

    public IrisException(String message) {
        super(message);
    }
}
