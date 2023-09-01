package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a Task with a duration.
 */
public class Event extends Task {
    /** Date and time the event starts **/
    private LocalDateTime from;
    /** Date and time the event starts **/
    private LocalDateTime to;

    /**
     * Instantiates an instance of Event.
     *
     * @param description Description of the Event task.
     * @param from Date and time the event starts.
     * @param to Date and time the event ends.
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

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon() == "X") {
            mark = 1;
        } else {
            mark = 0;
        }
        return "E | " + mark + " | " + super.writeToFile() + " | " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " -> " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
