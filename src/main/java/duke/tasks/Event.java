package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeIllegalArgumentException;

/**
 * A Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    // Error messages
    private static final String ERROR_MESSAGE_INVALID_DATE_FORMAT =
            "The start and end date/time of an Event task must follow the format yyyy-MM-dd HH:mm.";

    // Template Strings
    private static final String EVENT_DISPLAY_TEMPLATE = "[E]%s (from: %s to: %s)";
    private static final String EVENT_EXPORT_TEMPLATE = "EVENT || %s || %s || %s || %s";

    // The start and end date/time of the Event task, stored as LocalDateTime Objects.
    protected LocalDateTime start;
    protected LocalDateTime end;

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
            this.updateStart(start);
            this.updateEnd(end);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format(EVENT_DISPLAY_TEMPLATE,
                super.toString(), this.start.format(printFormatter), this.end.format(printFormatter));
    }

    /**
     * Exports the Event task to a String to be saved.
     *
     * @return String representation of the Event task to be saved.
     */
    @Override
    public String export() {
        return String.format(EVENT_EXPORT_TEMPLATE,
                super.export(), "", this.start.format(parseFormatter), this.end.format(parseFormatter));
    }

    /**
     * Updates the start date/time of the task.
     *
     * @param newStart The start date/time of the Event task. Must follow the format yyyy-MM-dd HH:mm.
     * @throws DukeIllegalArgumentException If the start/end date/time does not follow the format yyyy-MM-dd HH:mm.
     */
    public void updateStart(String newStart) throws DukeIllegalArgumentException {
        try {
            this.start = LocalDateTime.parse(newStart, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Updates the end date/time of the task.
     *
     * @param newEnd The end date/time of the Event task. Must follow the format yyyy-MM-dd HH:mm.
     * @throws DukeIllegalArgumentException If the start/end date/time does not follow the format yyyy-MM-dd HH:mm.
     */
    public void updateEnd(String newEnd) throws DukeIllegalArgumentException {
        try {
            this.end = LocalDateTime.parse(newEnd, parseFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INVALID_DATE_FORMAT);
        }
    }
}
