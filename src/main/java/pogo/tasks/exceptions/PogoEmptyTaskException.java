package pogo.tasks.exceptions;

import pogo.PogoException;

/**
 * Represents an exception that occurs when a task description is empty.
 */
public class PogoEmptyTaskException extends PogoException {
    /**
     * Constructs a PogoEmptyTaskException.
     */
    public PogoEmptyTaskException() {
        super("pogo.tasks.Task description cannot be empty");
    }
}
