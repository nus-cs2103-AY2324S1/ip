package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event Class.
 *
 * @author Shishir
 */
public class Event extends Task {

    /** Start date and time of the event. */
    private LocalDateTime from;

    /** End date and time of the event. */
    private LocalDateTime to;

    /**
     * Constructs the event class.
     * @param description Description of the task.
     * @param status Status of completion.
     * @param from Start date and time.
     * @param to End date and time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean status) {
        super(description, status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event.
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return "[Event] " + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ")";
    }

    /**
     * Returns the string representation of the event in file format.
     * @return String representation of the event in file format.
     */
    @Override
    public String toFile() {
        return "E" + super.toFile() + " | "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + " - " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }
}
