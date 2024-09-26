package anya.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Anya application.
 * A deadline task is a task with a specific due date and time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new `Deadline` instance with the specified description and deadline date-time.
     *
     * @param description A description of the deadline task.
     * @param by          The due date and time of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = super.convertStringToDate(by);
    }

    /**
     * Constructs a new `Deadline` instance with the specified description, deadline date-time, and done status.
     *
     * @param description A description of the deadline task.
     * @param by          The due date and time of the deadline task.
     * @param isDone      A boolean indicating whether the task is marked as done.
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.by = super.convertStringToDate(by);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns the type of task, which is 'D' for deadline.
     *
     * @return The task type.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Formats the `Deadline` object as a string for saving to storage.
     *
     * @return A string representation of the `Deadline` object suitable for storage.
     */
    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + this.by;
    }

    /**
     * Formats a `LocalDateTime` object as a string in the "MMM d yyyy HH:mm" format.
     *
     * @param date The `LocalDateTime` object to be formatted.
     * @return A formatted date-time string.
     */
    public String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(this.by) + ")";
    }
}
