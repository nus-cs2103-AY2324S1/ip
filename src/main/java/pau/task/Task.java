package pau.task;

/**
 * Represents a task.
 */
public abstract class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Status of task completion.
     */
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Writes the contents of the task to the hard disk.
     *
     * @return The contents of the task that is saved to the hard disk.
     */
    public abstract String writeToFile();

    /**
     * Returns the icon to indicate state of completion of task.
     *
     * @return "X" represents a completed task while " " represents an uncompleted task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as uncompleted.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Sets the state of the task when tasks are loaded.
     *
     * @param status String of an integer that represents state of the task.
     */
    public void setStatus(String status) {
        if ((status == "1")) {
            this.markAsDone();
        } else {
            this.markAsUndone();
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}