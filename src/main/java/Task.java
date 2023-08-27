/**
 * Abstract class which all the different types of tasks inherit from
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Class constructor specifying the description of the task.
     * @param description the string description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string indicator of whether the task is marked as completed
     * @return the string representation of whether the task is marked as completed
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the information associated with the task
     * @return the string representation of the task description and its state of completion
     */
    @Override
    public String toString() {
        String prefix = "";
        if (this.isDone) {
            prefix = "[X]";
        } else {
            prefix = "[ ]";
        }
        return prefix + " " + this.description;
    }

    public abstract String saveTask();
}