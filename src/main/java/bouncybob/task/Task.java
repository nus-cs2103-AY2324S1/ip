package bouncybob.task;

/**
 * Represents a task in the BouncyBob application.
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a new Task with the given name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Toggles the done status of the task.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUnDone() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description, which is the same as the name for a base task.
     */
    public String getDescription() {
        return getName();
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the symbol representing the task type.
     *
     * @return null for the base task type.
     */
    public String getSymbol() {
        return null;
    }

    /**
     * Converts the task to its file storage format.
     *
     * @return The string representation of the task in file storage format.
     */
    public String toFileFormat() {
        return getSymbol() + " | " + (isDone ? "1" : "0") + " | " + name;
    }
}
