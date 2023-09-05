package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents an event task with a specific start and end date.
 * It is a subclass of the Task class.
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event in string format (e.g., "yyyy-MM-dd").
     * @param to The end date of the event in string format (e.g., "yyyy-MM-dd").
     * @throws DukeException If there is an issue while parsing the dates or constructing the task.
     */
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
