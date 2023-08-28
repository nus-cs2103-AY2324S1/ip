package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The duke.tasks.Event class contains the tasks
 * with to and from date/time inputs.
 *
 * @author: Shishir
 **/
public class Event extends Task {

    /** The from date/time **/
    private LocalDateTime from;

    /** The to date/time **/
    private LocalDateTime to;

    /** The constructor.
     * @param description The description of the task.
     * @param from The from date/time
     * @param to The to date/time
     **/
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** The constructor.
     * @param description The description of the task.
     * @param status The status of completion.
     * @param from The from date/time.
     * @param to The to date/time.
     **/
    public Event(String description, LocalDateTime from, LocalDateTime to, String status) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[Event] " + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    public String toFile() {
        return "E" + super.toFile() + " | "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + " - " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }
}
