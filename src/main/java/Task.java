public abstract class Task {
    private String description;

    private boolean isDone;

    /**
     * Constructor
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return The icon which represent whether this task is done.
     */
    public String getStatusIcon() {
        return isDone ? "X": " ";
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark this task, mark this task as undone.
     */
    public void unmark() {
        this.isDone = false;

    }

    public String saveString() {
        return isDone ? "1/" + description : "0/" + description;
    }

    /**
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
