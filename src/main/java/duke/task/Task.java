package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * The Task class represents a generic task. It is an abstract class
 * that serves as the base class for Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs an instance of a Task.
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
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
        return;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
        return;
    }

    /**
     * Converts the task to a string format suitable for saving to a data file.
     *
     * @return A string representation of the task for data storage.
     */
    public abstract String toData();

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date The date string to parse.
     * @return A LocalDate object representing the parsed date.
     * @throws DukeException If there is an issue while parsing the date.
     */
    protected LocalDate parseDate(String date) throws DukeException {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format. Please enter date in yyyy-mm-dd format");
        }
        return localDate;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task with its status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
