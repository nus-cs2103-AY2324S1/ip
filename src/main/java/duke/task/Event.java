package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The Event class represents a task that takes place during a specific time frame.
 * It inherits from the Task class and adds start and end time fields.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    private final LocalDate from;

    /**
     * The end time of the event.
     */
    private final LocalDate to;

    /**
     * Constructs a new Event object with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the Event task.
     * @param isDone      The completion status of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     * The event is initially marked as not done.
     *
     * @param description The description of the Event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A formatted string indicating the task type, completion status, description, start time, and end time.
     */
    @Override
    public String toString() {
        String f = this.from.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String t = this.to.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return "[E] " + super.toString() + " (from: " + f + " to: " + t + ")";
    }

    /**
     * Returns a formatted string for writing the Event task to a file.
     *
     * @return A formatted string containing task details for file storage.
     */
    @Override
    public String writeFile() {
        return "E | " + super.writeFile() + " | " + this.from + " | " + this.to;
    }
}
