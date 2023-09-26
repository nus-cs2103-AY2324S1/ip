package duke.tasks;

/**
 * An extension of <code>Task</code>, a <code>ToDo</code> object keeps track of
 * a task that should be accomplished, but has no strict timeframe
 */

public class ToDo extends Task {
    /**
     * The class constructor.
     *
     * @param description Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string form of the <code>ToDo</code> task, for writing to file.
     */
    @Override
    public String storedString() {
        return "T | " + super.storedString();
    }

    /**
     * Returns the string form of the <code>ToDo</code> task, for display to user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

