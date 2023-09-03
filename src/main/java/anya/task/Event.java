package anya.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Anya application.
 * An event task is a task with a specific start date and time and an end date and time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new `Event` instance with the specified description, start date-time, and end date-time.
     *
     * @param description A description of the event task.
     * @param from        The start date and time of the event task.
     * @param to          The end date and time of the event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = super.convertStringToDate(from);
        this.to = super.convertStringToDate(to);
    }

    /**
     * Constructs a new `Event` instance with the specified description, start date-time, end date-time, and done status.
     *
     * @param description A description of the event task.
     * @param from        The start date and time of the event task.
     * @param to          The end date and time of the event task.
     * @param isDone      A boolean indicating whether the task is marked as done.
     */
    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = super.convertStringToDate(from);
        this.to = super.convertStringToDate(to);
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns the type of task, which is 'E' for event.
     *
     * @return The task type.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Formats the `Event` object as a string for saving to storage.
     *
     * @return A string representation of the `Event` object suitable for storage.
     */
    public String formatToSave() {
        return "E" + super.formatToSave() + " | " + this.from + " | " + this.to;
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
        return "[E]" + super.toString() + " (from: " + formatDate(this.from) + " to: " + formatDate(this.to) + ")";
    }
}
