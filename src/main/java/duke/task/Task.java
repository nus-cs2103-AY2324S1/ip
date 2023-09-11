package duke.task;

/**
 * Represents a Task class that deals with the task that has a description and can be marked as done.
 */
public class Task {
    public static final String DATE_FORMAT = "MMM dd yyyy";
    private final String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark isDone as true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark isDone as false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Return the status of the task by a tick or X symbol.
     *
     * @return String representation of tick or X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Get the description of the task.
     *
     * @return String representation description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the file descriptor of the task for parsing when reloaded.
     *
     * @return String representation of the file descriptor of the task.
     */
    public String getFileDescriptor() {
        return String.format("%s | %s ", this.isDone, this.getDescription());
    }

    /**
     * Get the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
