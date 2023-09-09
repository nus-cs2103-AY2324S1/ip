package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;



/**
 * Represents an event task with a start and end date.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate e_start;
    private LocalDate e_end;


    public LocalDate getE_start() {
        return e_start;
    }
    public LocalDate getE_end() {
        return e_end;
    }

    /**
     * Constructs a new Event with the specified description, start date, and end date.
     *
     * @param e_start     Start date of the event.
     * @param e_end       End date of the event.
     * @param description Description of the event.
     * @throws DukeException If the given date format is invalid.
     */
    public Event(String e_start, String e_end, String description) throws DukeException {
        super(description, TaskType.EVENT);
        try {
            this.e_start = LocalDate.parse(e_start, FORMATTER);
            this.e_end = LocalDate.parse(e_end, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }

    /**
     * Constructs a new Event with the specified description, combined time (start-end), and completion status.
     *
     * @param description Description of the event.
     * @param time Combined start and end date in the format 'start-end'.
     * @param isDone Boolean indicating the completion status of the event.
     * @throws DukeException If the given date format is invalid.
     */
    public Event(String description, String time, Boolean isDone) throws DukeException {
        super(description, TaskType.EVENT, isDone);
        try {
            String[] splits = time.split("-");
            this.e_start = LocalDate.parse(splits[0].trim(), FORMATTER);
            this.e_end = LocalDate.parse(splits[1].trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + e_start.format(OUTPUT_FORMATTER)
                + " to: " + e_end.format(OUTPUT_FORMATTER) + ")";
    }
}
