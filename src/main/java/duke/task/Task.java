package duke.task;

import duke.exception.DukeException;

/**
 * Represents a general task with a description and status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private String horizontal = "____________________________________________________________";

    /**
     * Constructs a Task instance with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " (space) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the number representation of the task's status.
     *
     * @return "1" if the task is done, "0" if the task is not done.
     */
    public String getNumber() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    };

    /**
     * Unmarks the task if it's marked as done.
     *
     * @throws DukeException If the task is not marked as done.
     */
    public void unmark() throws DukeException {
        if (!this.isDone) {
            throw new DukeException(horizontal + "\nOOPS!!! You did not mark this task.\n" + horizontal);
        }
        this.isDone = false;
    };

    /**
     * Returns a string representation of the Task.
     *
     * @return A string containing the task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +  "] " + this.description;
    }
}