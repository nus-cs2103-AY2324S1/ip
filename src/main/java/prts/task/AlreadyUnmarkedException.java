package prts.task;

import prts.PrtsException;

/**
 * Signals that the user is trying to unmark a task that already isn't marked.
 * Is only thrown by the <code>unmark()</code> function in the Task object.
 */
public class AlreadyUnmarkedException extends PrtsException {
    public AlreadyUnmarkedException() {
        super("This hasn't been done yet...");
    }
}
