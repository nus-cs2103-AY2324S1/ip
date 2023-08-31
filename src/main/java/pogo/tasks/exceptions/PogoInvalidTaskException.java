package pogo.tasks.exceptions;

import pogo.PogoException;

/**
 * Represents an exception that occurs when a task description is empty.
 */
public class PogoInvalidTaskException extends PogoException {
    /**
     * Creates a PogoInvalidTaskException.
     */
    public PogoInvalidTaskException() {
        super("Invalid task type");
    }

    /**
     * Creates a PogoInvalidTaskException with a custom message.
     *
     * @param message Custom message.
     */
    public PogoInvalidTaskException(String message) {
        super(message);
    }
}
