package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Abstraction for task that have start and end dates. */
public class Event extends Task {

    /** Start time of event. */
    protected LocalDate start;
    /** End time of event. */
    protected LocalDate end;

    /** Creates a new Event object.
     *
     * @param description What the event is about.
     * @param isDone Whether the event is done or not.
     * @param start Start date of the event.
     * @param end End date of the event.
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "E " + isCompleted + " " + this.description +
                "/from" + this.start + "/to" + this.end;
    }

    /**
     * Display string representation of an event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " +
                this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
