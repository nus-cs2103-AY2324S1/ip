package duke;

/**
 * Represents a task.
 */
public class Task {
    
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to initialise a Task object.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if task is marked as done and " " otherwise.
     *
     * @return String representation of whether a task is done or undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns description of task.
     *
     * @return Description of task as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts the Task object to the specific format to be stored in the text file.
     *
     * @return The specific string representation of the Task object to be stored.
     */
    public String convertToSavedString() {
        return String.format("//%s//%s", this.getStatusIcon(), this.description);
    }

    /**
     * Converts the Task object to its string representation.
     *
     * @return The string representation of the Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
