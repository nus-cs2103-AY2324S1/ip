package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task.
 * An Event task is a task that occurs between a specific start and end date and time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start, and end dates.
     *
     * @param description Description of the task.
     * @param from The start date and time of the event in the format "d/M/yyyy HHmm".
     * @param to The end date and time of the event in the format "d/M/yyyy HHmm".
     * @throws DukeException If the provided date format is incorrect.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format provided. Please use the format d/M/yyyy HHmm.");
        }
    }

    /**
     * Retrieves the start date and time of the event.
     *
     * @return The start date and time as a LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Retrieves the end date and time of the event.
     *
     * @return The end date and time as a LocalDateTime.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Converts the Event task to a string representation.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Converts the Event task to a string representation suitable for file storage.
     *
     * @return String representation of the Event task for file storage.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
