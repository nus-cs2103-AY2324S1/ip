public class Task {
    /**
     * Task description.
     */
    private final String description;

    /**
     * Task status.
     */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description of the task.
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
     * Get the status icon of the task.
     *
     * @return tick or X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : ""); // return tick or X symbols
    }

    /**
     * Get the description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public String getFileDescriptor() {
        return String.format("%s | %s ", this.isDone, this.getDescription());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
