package duke.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;
    /**
     * Constructs a Task object with the given description.
     *
     * This constructor initializes a Task object with the provided description and sets its initial
     * "done" status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }
    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }
    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    public String getType() {
        return "Task";
    }
    /**
     * Marks the task based on the value read from a file.
     *
     * @param isMarked The value indicating whether the task is marked.
     */
    public void markFromRead(String isMarked) {
        if (isMarked.equals("1")) {
            this.isDone = true;
        }
    }
    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    /**
     * Gets the status of the task from the file.
     *
     * @return The status of the task (1 for done, 0 for not done).
     */
    public int getStatusFromFile() {
        return (isDone ? 1 : 0);
    }
    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a string representation of the task for writing to a file.
     *
     * @return A string representation of the task for file output.
     */
    public String toStringFile() {
        return getStatusFromFile() + " | " + description;
    }

    public String getDescription() {
        return this.description;
    }
}
