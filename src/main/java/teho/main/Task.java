package teho.main;

/**
 * Represents a task with only a description and status.
 */
public abstract class Task {
    /**
     * Description of task.
     */
    protected String description;
    /**
     * Status of task.
     */
    protected boolean isDone;

    /**
     * Constructs new Task with description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of the task.
     * If status icon is "X", task is done.
     * If status icon is " ", task is undone.
     *
     * @return Status icon of task ("X" if task is done, " " if task is undone).
     */
    public String getStatusIcon() { //change to separate it with the description
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    /**
     * Returns string representation of a task, including the status icon and description of task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Marks task as done
     *
     * @param task Index of task to be marked as done.
     */
    public void markAsDone(int task) {
        this.isDone = true;
    }

    /**
     * Marks task as undone
     *
     * @param task Index of task to be marked as undone.
     */
    public void markAsNotDone(int task) {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task details for saving task in file.
     *
     * @return String representation of the Deadline task.
     */
    public abstract String fileString();
}
