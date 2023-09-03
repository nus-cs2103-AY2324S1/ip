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
     * @param start Start time of Event.
     * @param end End time of Event.
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
     * @param start Start time of Event as LocalDate.
     * @param end End time of Event as LocalDate.
     * */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * toString method for Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "+ end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
