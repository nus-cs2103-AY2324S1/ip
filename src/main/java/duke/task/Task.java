package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * Represents a task that can be added to a task list.
 */
public abstract class Task {

    protected enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    protected String description;
    protected boolean isDone;

    /**
     * Converts the task to a string for saving to the data file.
     *
     * @return A string representation of the task for saving.
     */
    public abstract String toSave();

    /**
     * Constructs a `Task` object with a description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return "X" if the task is done, " " (space) if the task is not done.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public String markTask() {
        this.isDone = true;
        return "Heyyo! I've marked this task as done!\n" + this;
    }

    /**
     * Unmarks the task as done.
     */
    public String unmarkTask() {
        this.isDone = false;
        return "Aww snap! I've unmarked this task!\n" + this;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public abstract Type getType();

    public abstract LocalDate getCompareDate();

    /**
     * Returns a string representation of the `Task` for display.
     *
     * @return A string representation of the task in the format "[X] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }



    /**
     * Parses a date string and returns a LocalDate object.
     *
     * @param date The date string in the format "yyyy-MM-dd" to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DukeException If the date string is invalid or cannot be parsed.
     */
    public LocalDate setDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date format or date does not exist!");
        }
    }
}
