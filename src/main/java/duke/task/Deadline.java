package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor to Deadline Task.
     *
     * @param description description of task
     * @param by          date task is due
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns if task is before given date.
     *
     * @param date given date to check against
     * @return true if task is before given date, false otherwise
     */
    @Override
    public boolean isBefore(LocalDate date) {
        return this.by.isBefore(date);
    }

    /**
     * Get string representation of task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Get a string representing this event to save to file.
     *
     * @return string representing this event to save to file
     */
    @Override
    public String getSaveString() {

        return String.format("%d deadline %s /by %s", isDone ? 1 : 0, description.trim(), by);
    }
}
