package task;

/**
 * The Task class is an abstract base class for different types of tasks.
 * It provides methods to mark tasks as done and generate file strings.
 */
public abstract class Task {

    protected Boolean done;

    /**
     * Constructs a Task object with the specified done status.
     *
     * @param done The done status of the task.
     */
    public Task(boolean done) {
        this.done = done;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * Checks if title of task contains the query.
     *
     * @return A Boolean value representing if title contains query.
     */
    public abstract Boolean compareTitle(String query);

    /**
     * Generates a string representation of the task for storage in a file.
     *
     * @return A formatted string representing the task.
     */
    public String toFileString() {
        return "task";
    }
}
