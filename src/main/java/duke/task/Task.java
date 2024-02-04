package duke.task;

/**
 * Represents a task with a description, a type, and a status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Initialises the task with the given description and type.
     * 
     * @param description The description of the task.
     * @param type        The type of the task.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return The status icon of the task.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Changes the status of the task.
     * 
     * @param isDone The new status of the task.
     */
    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task to be written to the file.
     * 
     * @return The string representation of the task to be written to the file.
     */
    public String toFileString() {
        return this.type + " | " + (isDone ? "1" : "0") + " | " + description;
    }
}