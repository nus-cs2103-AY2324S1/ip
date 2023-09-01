package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represent tasks of type event.
 *
 * @author Armando Jovan Kusuma
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates a event task with the specified
     * description, start date, and end date.
     *
     * @param description the description of the event task.
     * @param from the start date.
     * @param to the end date.
     * @throws DukeException If the date format is wrong.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Please input in the form of yyyy-MM-dd");
        }
    }

    /**
     * Returns the string description of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
