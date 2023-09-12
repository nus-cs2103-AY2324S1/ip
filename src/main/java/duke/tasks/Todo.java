package duke.tasks;

/**
 * Special kind of task that only has a description
 */
public class Todo extends Task {
    protected String type = "T";

    /**
     * Constructor for the Todo task type.
     * @param description String describing what the Todo task is about
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Mark this Todo task as done.
     */
    @Override
    public void mark() {
        this.isDone = true;
    }
    /**
     * Unmark this Todo task as not done yet.
     */
    @Override
    public void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return this.type + " | " + this.getStatusIcon()
                + " | " + this.description;
    }
}