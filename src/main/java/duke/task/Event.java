package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An Event task.
 */
public class Event extends Task {
    /** Datetime of start and end of Event. **/
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor of the Event instance.
     * @param description string description
     * @param from Datetime of start of event
     * @param to Datetime of end of event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    /**
     * Returns string representation of task to be written into save file.
     * @return string representation of task
     */
    public String toWriteString() {
        return "E | " + super.toWriteString() + " | " + this.from + " | " + this.to;
    }
}
