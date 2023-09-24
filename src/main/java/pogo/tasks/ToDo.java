package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * Represents a generic task to be done.
 */
public class ToDo extends Task {
    public ToDo(String description) throws PogoInvalidTaskException {
        super(description);
    }

    @Override
    public String getStatusMessage() {
        return "[T]" + super.getStatusMessage();
    }

    /**
     * Accepts a visitor that performs an action on the task.
     *
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }
}
