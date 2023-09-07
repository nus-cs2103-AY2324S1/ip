package prts.task;

import prts.PrtsException;

/**
 * Signals that the user is trying to mark a task that is already marked.
 * Is only thrown by the <code>mark()</code> function in the Task object.
 */
public class AlreadyMarkedException extends PrtsException {
    public AlreadyMarkedException() {
        super("It's already done.");
    }
}
