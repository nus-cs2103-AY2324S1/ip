package koko;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with a name, start date, and end date.
 */
public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an Event with the specified name, start date, and end date.
     *
     * @param name      The name of the event.
     * @param startDate The start date of the event.
     * @param endDate   The end date of the event.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates an Event object based on its file representation.
     *
     * @param parts The components of the event, parsed from file.
     * @return A new Event object.
     * @throws DukeException If the date format is invalid.
     */
    public static Event fromFileFormat(String[] parts) throws DukeException {
        boolean isDone = "1".equals(parts[1].trim());
        String name = parts[2].trim();
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.parse(parts[3].trim());
            endDate = LocalDate.parse(parts[4].trim());
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("Event /from or /to dates should be in yyyy-mm-dd format (e.g. 2023-08-25)");
        }

        Event event = new Event(name, startDate, endDate);
        if (isDone) {
            event.markAsDone();
        }
        return event;
    }

    /**
     * Converts the Event object to its file representation format.
     *
     * @return A string representation of the Event object for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E|" + (isDone ? "1" : "0") + "|" + this.name + "|" + this.startDate + "|" + this.endDate;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation including event type, status, name, and date range.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStart = this.startDate.format(formatter);
        String formattedEnd = this.endDate.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
