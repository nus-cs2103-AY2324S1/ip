package duke.task;

import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the event task.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for event task.
     * @param description Description of the event
     * @param from Start time of the event
     * @param to End time of the event
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
            if (this.from.isAfter(this.to)) {
                throw new DukeException("Start date cannot be after end date");
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        assert (from != null) : "From time of event task cannot be empty.";
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        assert (to != null) : "To time of event task cannot be empty.";
        String toFormatted = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }

}
