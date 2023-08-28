package duke.task;

/**
 * The Task class is used to represent a task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * The constructor for a Task.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * This method is used to mark the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * This method is used to return the string representation of a Task.
     *
     * @return Returns the string represenation of a Task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return String.format("[X] %s", description);
        } else {
            return String.format("[ ] %s", description);
        }
    }
}
