package pippi.task;

/**
 * The Task class represents the overarching task representation
 *
 * @author Nathan
 */
public class Task {
    private String description;
    private boolean isDone;
    /**
     * Constructs a Task instance with the specified task description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status of a task presented to the UI
     * @return completion status string of a task
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the completion status of a task written to memory
     * @return completion status string of a task
     */
    public String getStatus() {
        return (this.isDone ? "| 1 | " : "| 0 | ");
    }

    /**
     * Returns the description of a task
     * @return description string of a task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the status of a task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the status of a task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    public String toMemory() {
        return this.toString();
    }

    /**
     * Returns the common representation of a task to the UI
     * @return Task string representation
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.getDescription();
    }

}
