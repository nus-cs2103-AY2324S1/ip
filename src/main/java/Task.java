/**
 * An abstract class that represents a task.
 */
public abstract class Task {

    // The description of the task.
    private String description;

    // Whether the task is done.
    private boolean isDone;

    /**
     * Creates a new Task object.
     * 
     * @param input The description of the task.
     */
    public Task(String input) {
        this.description = input;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public abstract String getSaveFormat();

    /**
     * Returns the string representation of the task.
     * 
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + " " + this.description;
    }
}
