package ekud.state;

import ekud.util.DateTime;

/**
 * Represents a task to be completed within a deadline.
 */
public final class DeadlineTask extends Task {
    private final DateTime by;

    /**
     * Creates a new task with a deadline.
     * 
     * @param title  The title of the task.
     * @param isDone Whether the task is already completed.
     * @param by     The deadline of the task.
     */
    public DeadlineTask(String title, boolean isDone, DateTime by) {
        super(title, isDone);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     * 
     * @return The deadline.
     */
    public DateTime getBy() {
        return by;
    }

    /**
     * Returns the string representation of this task, to be displayed to the user.
     * 
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    @Override
    public int hashCode() {
        return super.hashCode() + by.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        if (!(other instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask otherTask = (DeadlineTask) other;
        return by.equals(otherTask.by);
    }
}
