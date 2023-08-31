package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the chat bot.
 * Event tasks start at a specific date/time and end at a specific date/time.
 */
public class Event extends Task {

    private final LocalDateTime start;
    private final LocalDateTime deadline;

    /**
     * Constructs an event task with the given description, start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param start       The start date/time of the event.
     * @param deadline    The end date/time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime deadline) {
        super(description);
        this.start = start;
        this.deadline = deadline;
    }

    /**
     * Constructs an event task with the given description, start date/time, end date/time, and done status.
     *
     * @param description The description of the event task.
     * @param start       The start date/time of the event.
     * @param deadline    The end date/time of the event.
     * @param isDone      The done status of the task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(),
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")));
    }

    /**
     * Formats the event task for storage in a data file.
     *
     * @return The formatted string for storage.
     */
    @Override
    public String formatForStorage() {
        return String.format("E | %s | %s--%s", super.formatForStorage(), start, deadline);
    }

    /**
     * Checks if the event task is within the given date range.
     *
     * @param date The date to check against.
     * @return True if the event task is within the date range, false otherwise.
     */
    @Override
    public boolean isWithinDateRange(LocalDateTime date) {
        return start.isBefore(date) && deadline.isAfter(date);
    }
}
