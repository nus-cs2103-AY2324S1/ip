package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Start time of Event. */
    protected LocalDate start;
    /** End time of Event. */
    protected LocalDate end;

    /**
     * Constructor for Event.
     *
     * @param description Description of task.
     * @param start Start time of event.
     * @param end End time of event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Constructor for Event loaded from Storage.
     *
     * @param description Description of task.
     * @param start Start time of event as LocalDate.
     * @param end End time of event as LocalDate.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * toString method for Event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "+ end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
