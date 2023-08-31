package ekud.state;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    private final String title;
    private boolean isDone;

    protected Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Returns the title of the task.
     * 
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns whether the task has been completed.
     * 
     * @return Whether the task is complete.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the string representation of this task, to be displayed to the user.
     * 
     * @return The string representation.
     */
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + title;
    }
}
