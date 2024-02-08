package valerie;

/**
 * The Task class represents a task in the Duke application.
 * It contains fields and methods to manage task descriptions, completion status,
 * and generating formatted string representations for display and storage.
 */
public class Task {
    /** Class field description that describes the task. */
    protected String description;
    /** Class field isDone that tells whether the task is marked. */
    protected boolean isDone;

    /**
     * Constructor to initialize the Task class.
     *
     * @param description Describes the task.
     */
    public Task(String description) {
        // Add an assertion to check if description is not null
        assert description != null : "Description cannot be null";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to get the status icon of the task.
     *
     * @return String "X" if task's isDone is true, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks if the task is done.
     *
     * @return `true` if the task is done, otherwise `false`.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Method that sets the task's isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that sets the task's isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Generates a string representation of the task for file storage.
     *
     * @return A formatted string representation of the task for file storage.
     */
    public String toFileString() {
        String doneStatus = isDone ? "1" : "0";
        return String.format("  | %s | %s", doneStatus, this.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
