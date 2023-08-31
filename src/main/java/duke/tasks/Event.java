package duke.tasks;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    /** The start time of the Event task. */
    protected String start;

    /** The end time of the Event task. */
    protected String end;

    /** The input formatter to parse Date and Time input. */
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** The output formatter to format Date and Time output. */
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");

    /**
     * Constructor for an Event task.
     *
     * @param description The description of the Event task.
     * @param startTime The start time of the Event task. Must follow the format yyyy-MM-dd HH:mm.
     * @param endTime The end time of the Event task. Must follow the format yyyy-MM-dd HH:mm.
     * @throws DukeException If the times do not follow the format yyyy-MM-dd HH:mm.
     */
    public Event(String description, String startTime, String endTime) throws DukeException {
        super(description);

        try {
            LocalDateTime parseStart = LocalDateTime.parse(startTime, inputFormatter);
            LocalDateTime parseEnd = LocalDateTime.parse(endTime, inputFormatter);
            start = parseStart.format(outputFormatter);
            end = parseEnd.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Event times must be in this format: yyyy-mm-dd HH:mm");
        }

    }

    /**
     * Returns the Formatted String to be saved into Storage.
     *
     * @return Formatted String representation of the Event task.
     */
    public String exportData() {
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.start + "-" + this.end;
    }

    /**
     * Returns the String representation of the Event task.
     *
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
