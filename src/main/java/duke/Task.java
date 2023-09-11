package duke;

import java.time.LocalDateTime;

/**
 * Represents a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a task.
     *
     * @param description the task details.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        assert this.description != null : "description should not be null";
    }

    /**
     * Checks if the task has been completed and
     * return the status icon of the task.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // Mark a task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Mark a task as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of the task in file format.
     *
     * @return the string representation in file format.
     */
    abstract String toFileFormat();

    /**
     * Returns the type of the task.
     *
     * @return the type of the task.
     */
    abstract String getTaskType();

    /**
     * Updates the todo task.
     *
     * @param description the task details.
     * @throws DukeException if invalid or incorrect command.
     */
    public abstract void updateTask(String description) throws DukeException;

    /**
     * Updates the deadline task.
     *
     * @param description the task details.
     * @param by the deadline for the task.
     * @throws DukeException if invalid or incorrect command.
     */
    public abstract void updateTask(String description, LocalDateTime by) throws DukeException;

    /**
     * Updates the event task.
     *
     * @param description the task details.
     * @param from the start date/time for the task.
     * @param to the end date/time for the task.
     * @throws DukeException if invalid or incorrect command.
     */
    public abstract void updateTask(String description, LocalDateTime from, LocalDateTime to) throws DukeException;
}
