package extensions.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import extensions.exceptions.DukeIllegalArgumentException;

/**
 * A Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    // The start and end date/time of the Event task, stored as LocalDateTime Objects.
    protected final LocalDateTime start;
    protected final LocalDateTime end;

    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param start The start date/time of the Event task. Must follow the format yyyy-MM-dd HH:mm.
     * @param end The end date/time of the Event task. Must follow the format yyyy-MM-dd HH:mm.
     * @throws DukeIllegalArgumentException If the start/end date/time does not follow the format yyyy-MM-dd HH:mm.
     */
    public Event(String description, String start, String end) {
        super(description);
        try {
            this.start = LocalDateTime.parse(start, parseFormatter);
            this.end = LocalDateTime.parse(end, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentException(
                    "The start and end date/time of an Event task must follow the format yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.start.format(printFormatter), this.end.format(printFormatter));
    }

    /**
     * Exports the Event task to a String to be saved.
     *
     * @return String representation of the Event task to be saved.
     */
    @Override
    public String export() {
        return String.format("EVENT || %s || %s || %s || %s", super.export(), "",
                this.start.format(parseFormatter), this.end.format(parseFormatter));
    }
}
