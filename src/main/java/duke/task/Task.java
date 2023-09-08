package duke.task;

/**
 * Represents a task, the basis for ToDo, Event and Deadline class.
 */
public class Task {
    /** Represents the description and the status of the task. */
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description Task description.
     * @param isDone Status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a Task, by default isDone is false.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Represents a string representation to be written to an external file.
     *
     * @return A String representation.
     */
    public String stringToFile() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    /**
     * Checks whether the Task object contains the keyword.
     *
     * @param keyword Keyword.
     * @return Whether the Task object contains the keyword.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Converts isDone, a boolean, to a status icon for printing.
     *
     * @return An icon String.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markIsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
