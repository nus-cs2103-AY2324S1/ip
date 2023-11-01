package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * A <code>Event</code> object corresponds to an event task that has a description and a start date and end date.
 */
public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructs a <code>Event</code> object.
     * @param description The description of the event task.
     * @param start The start date of the event task.
     * @param end The end date of the event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    /**
     * Converts an event task to file format.
     * @return The start date and end date of the event task.
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + " | " + end;
    }

    /**
     * Converts an event task from file format.
     * @return The start date and end date of the event task.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    /**
     * Returns the string representation of an event task.
     * @return The string representation of an event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (from: " + start.format(formatter) +
                " to: " + end.format(formatter) + ")";
    }
}