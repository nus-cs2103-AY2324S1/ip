package duke;

import java.time.LocalDateTime;

/**
 * This class encapsulates an Event task that has the date/time to indicate when the event starts and end.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event.
     *
     * @param description the description of the event task.
     * @param from the date/time of when the event task starts.
     * @param to the date/time of when the event task ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + convertDateToString(this.from)
                + " to: " + convertDateToString(this.to) + ")";
    }

    @Override
    public String convertToSaveFormat() {
        return "E | " + super.convertToSaveFormat() + " | " + convertDateToString(this.from)
                + " to " + convertDateToString(this.to);
    }
}
