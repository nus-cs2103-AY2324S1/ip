package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = parseDate(from);
        this.to = parseDate(to);
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "E | " + done + " | " + this.description + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
