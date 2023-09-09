package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import duke.exceptions.DukeException;


/**
 * The Event class, which is a Task that has a specified start and end date.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Instantiates a new Event.
     *
     * @param name the description of the Event
     * @param from the start date
     * @param to   the end date
     * @throws DukeException the duke exception
     */
    public Event(String name, String from, String to) throws DukeException {
        super(name);
        if (name.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Follow the format for an event.");
        }
    }

    /**
     * Returns the String description of a task suitable for file storage.
     *
     * @return the task String
     */
    public String dataString() {
        if (this.isdone()) {
            return "T : 1 : " + this.getname() + ":" + this.from + ":" + this.to;
        } else {
            return "T : 0 : " + this.getname() + ":" + this.from + ":" + this.to;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        return "[E]" + super.toString() + " (from: " + this.from.format(format) +
                ") (to: " + this.to.format(format) + ")";
    }
}
