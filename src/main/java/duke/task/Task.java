package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;

/**
 * Represents a generic task within the chat bot application.
 */
public abstract class Task {

    /**
     * The duke.task.Task Type of the duke.task.Task.
     */
    protected Task.TaskType TaskType;
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The completion status of the task.
     */
    protected Boolean isDone;

    /**
     * Constructs a duke.task.Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) throws DukeException {
        if (description.trim().length() == 0) {
            throw new DukeException("The description cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task including the completion status
     * and the description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    /**
     * Used for easier readability during
     * storing into the file.
     *
     * @return the raw version of the task for storing.
     */
    public abstract String getRaw();

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Determine if the task is on a specific date.
     *
     * @param date to compare.
     * @return true if the task is at a date.
     */
    public boolean onDate(LocalDate date) {
        return false;
    }

    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Three different task types
     */
    protected enum TaskType {
        TODO, DEADLINE, EVENT
    }
}
