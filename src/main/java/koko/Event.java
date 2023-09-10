package koko;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an Event with the specified name, start time, and end time.
     *
     * @param name      The name of the event.
     * @param startDate The start time of the event.
     * @param endDate   The end time of the event.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

    @Override
    public String toFileFormat() {
        return "E|" + (isDone ? "1" : "0") + "|" + this.name + "|" + this.startDate + "|" + this.endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStart = this.startDate.format(formatter);
        String formattedEnd = this.endDate.format(formatter);
        return "[E]" + super.toString() + " (from: " + formattedStart + " to: " + formattedEnd + ")";
    }
}
