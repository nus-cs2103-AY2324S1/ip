package duke;

/**
 * Represents a task with a description and completion status.
 */
public class Task{
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a formatted string of the task for file storage.
     *
     * @return A String representation of the task.
     */
    public String toFileString() {
        String isDone;
        if (this.isDone) {
            isDone = "T";
        } else {
            isDone = "F";
        }
        return " | " + isDone + " | " + this.description;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return A String representation of the task.
     */
    @Override
    public String toString() {
        return (
                isDone
                    ? "[" + "X" + "] " + description
                    : "[" + " " + "] " + description
        );
    }
}
