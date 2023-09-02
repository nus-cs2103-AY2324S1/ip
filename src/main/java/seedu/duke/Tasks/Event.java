package seedu.duke.Tasks;


import seedu.duke.Exceptions.InvalidEventException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Event extends Task {
    /**
     * A child class of duke.Tasks to create tasks with a start time & end time.
     */

    protected LocalDate start;
    protected LocalDate end;
    public Event(String description, String start, String end) throws InvalidEventException {
        super(description);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeException e) {
            throw new InvalidEventException("Invalid date");
        }

    }

    public Event(String description, String start, String end, boolean isDone) throws InvalidEventException {
        super(description, isDone);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeException e) {
            throw new InvalidEventException("Invalid date");
        }
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String addDataFormat() {
        return "E " + super.addDataFormat() + " | " + start + " | " + end;
    }
}
