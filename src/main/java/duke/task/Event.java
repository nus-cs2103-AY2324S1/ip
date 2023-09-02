package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents an Event.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructor for Event.
     *
     * @param description String describing the Event.
     * @param start Start date in MMM DD YYYY format.
     * @param end End date in MMM DD YYYY format.
     */
    public Event(String description, LocalDate start,LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event.
     *
     * @return string representation of the Event task.
     */
    @Override
    public String toString() {
        String msg = "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")";
        return msg;
    }
}
