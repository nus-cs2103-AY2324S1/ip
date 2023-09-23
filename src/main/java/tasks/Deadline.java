package tasks;

import java.time.LocalDateTime;

import duke.DukeException;

/**
 * Represents a deadline task, which extends the Task class.
 * A Deadline task is characterized by a description and a deadline date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the specified description and deadline.
     *
     * @param description The description or title of the deadline task.
     * @param by The deadline date/time given in string format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = stringToDate(by);
    }

    /**
     * Retrieves the deadline date/time of this task.
     *
     * @return The LocalDateTime representing the deadline date/time.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the string representation of this Deadline task, which includes the task type identifier "[D]",
     * marking status, task description, and the deadline date/time.
     *
     * @return The string representation of this Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString(by) + ")";
    }
}
