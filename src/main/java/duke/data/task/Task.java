package duke.data.task;

/**
 * The Task class represents a task with a description and tracks whether it is completed.
 */
public class Task {
    /** Description of the task */
    protected String description;
    /** Whether the task is marked as done */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description, initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone flag to false.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Checks whether the task is marked as done.
     *
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is marked as done, an empty string if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks whether the user's search query exists in the description of the task.
     *
     * @param query The search query entered by the user.
     * @return true if the query exists in the description of the task, false otherwise.
     */
    public boolean contains(String query) {
        return description.contains(query);
    }

    /**
     * Gets the formatted string representation of the task for writing to a .txt file.
     *
     * @return A formatted string representation of the task.
     */
    public String toWrite() {
        if (isDone) {
            return "1 | " + description;
        }
        return "0 | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
