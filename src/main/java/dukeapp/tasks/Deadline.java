package dukeapp.tasks;

import dukeapp.exceptions.InsufficientArgumentsException;

/**
 * Represents a task containing a description, and the due time.
 */
public class Deadline extends Task {
    protected String by;

    protected Deadline(String description, String by) {
        this(description, false, by);
    }

    protected Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String encode() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0,
                this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}