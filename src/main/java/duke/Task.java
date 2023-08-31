package duke;

public abstract class Task {

    /** Description of the task. */
    protected String description;
    /** State of task if completed or not. */
    protected boolean isDone;

    private Ui ui = new Ui();

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Marks the task if state of task is completed.
     *
     * @return "X" if completed empty string if otherwise.
     * */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Change the state of task to completed. */
    public void markAsDone() {
        this.isDone = true;
        this.ui.showTaskMarked(this);
    }

    /** Change the state of task to not completed. */
    public void markAsUndone() {
        this.isDone = false;
        this.ui.showTaskUnmarked(this);
    }

    /** Get the string representation of the task for storage in hard drive.
     *
     * @return The string representation for storage purposes.
     */
    public abstract String getStorageDescription();

    /**
     * Display string representation of task.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
