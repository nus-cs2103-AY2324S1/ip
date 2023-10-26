package jarvis.tasks;

/**
 * Represents the Abstract Task Class.
 * Has three subclasses Event, Todo and Deadline.
 *
 * @author Shishir
 */
public abstract class Task {

    /** Description of the task. */
    private String description;

    /** Completion status of the task. */
    private boolean isCompleted;

    /**
     * Constructs the task class.
     * @param action Description of the task.
     */
    public Task(String action) {
        assert !action.isBlank() : "Description of task is empty!";
        this.description = action;
        this.isCompleted = false;
    }

    /**
     * Constructs the task class.
     * @param action Description of the task.
     * @param status Status of completion of the task.
     */
    public Task(String action, boolean status) {
        assert !action.isBlank() : "Description of task is empty!";
        this.description = action;
        this.isCompleted = status;
    }

    /**
     * Returns the string representation of the task.
     * @return Required string representation.
     */
    @Override
    public String toString() {
        return (this.isCompleted ? "\u2611" : "\u2610") + " " + this.description;
    }

    /**
     * Returns the string representation in the file format.
     * @return Required string representation in file format.
     */
    public String toFile() {
        return " | " + (this.isCompleted ? "X" : "O") + " | " + this.description;
    }

    /**
     * Sets the completion status of the task to given boolean value.
     * @param isMark Given boolean value.
     */
    public void completeTask(boolean isMark) {
        this.isCompleted = isMark;
    }

    /**
     * Returns the status of a task.
     * @return Status of completion.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    public boolean contains(String name) {
        return this.description.contains(name);
    }

    /**
     * Returns whether task has passed the stipulated date.
     * @return True if task has passed the stipulated date, false otherwise.
     */
    public abstract boolean hasPassed();

}
