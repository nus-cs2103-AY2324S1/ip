package duke.task;

import duke.DukeException;
import duke.task.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is an abstraction of an Event containing a description, a start date
 * and an end date. It is an extension from the Task Class.
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs an Event Object.
     *
     * @param description A String containing the description of the event.
     * @param start A String containing the start date of the event in YYYY-MM-DD format.
     * @param end A String containing the end date of the event in YYYY-MM-DD format.
     * @throws DukeException When either dates are not in YYYY-MM-DD format.
     */
    public Event(String description, String start, String end) throws DukeException {
        super(description);
        try {
            this.start = formatDate(start);
            this.end = formatDate(end);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your dates in YYYY-MM-DD format!");
        }
    }

    /**
     * Formats date and parses it into a LocalDate object.
     *
     * @param date String containing date in YYYY-MM-DD format to be parsed.
     */
    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Returns the String representation of the Event that is writeable to the data file.
     *
     * @return A String object that has a correct format to be written to data file.
     */
    @Override
    public String save() {
        return "E|" + super.save() + "|" + this.start + "|" + this.end;
    }

    /**
     * Returns a String representation of the Event Object.
     *
     * @return A String representation of the Event Object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
