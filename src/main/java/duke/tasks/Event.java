package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

/**
 * Represents an Event task object.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event object with given description and start/end time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Represents Event task in string format.
     *
     * @return String representation of Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateTimeFormatter(from) + " to: "
                + Parser.dateTimeFormatter(to) + ")";
    }

    /**
     * Represents Event task in string format to be stored.
     *
     * @return String representation of Event task.
     */
    @Override
    public String storageFormat() {
        return ("E" + super.storageFormat() + " | " + from + " | " + to);
    }
}
