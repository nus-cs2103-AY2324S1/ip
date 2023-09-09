package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task which is a specific type of task
 * that has a start time and an end time for the event.
 */
public class Event extends Task {

    /** The start time of the event. */
    protected LocalDateTime from;

    /** The end time of the event. */
    protected LocalDateTime to;

    /**
     * Creates a new Event task with the provided description, start time, and end time.
     *
     * @param description The description or details of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super("EVENT", description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event object from a string formatted for file storage.
     *
     * @param fileString A string containing the event details in a file-friendly format.
     * @return An Event object constructed from the given string.
     */
    public static Event fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();
        String from = parts[3].trim();
        String to = parts[4].trim();

        Event event = new Event(taskName, parseDateTime(from), parseDateTime(to));

        if (isCompleted.equals("1")) {
            event.setCompleted();
        }

        return event;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Converts the Event task details into a human-readable string.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        String fromFormat = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String toFormat = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[E] " + super.toString()
                + " (from: " + fromFormat
                + " to: " + toFormat
                + ")";
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param dateTimeString The date-time string to parse.
     * @return A LocalDateTime object representing the parsed date-time.
     */
    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Converts the Event task details into a string format suitable for file storage.
     *
     * @return A string representation of the Event task for file storage.
     */
    @Override
    public String toFileString() {
        return TaskType.EVENT + " | " + (isCompleted() ? "1" : "0") + " | " + getDescription() + " | " + getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
