package oscar.info;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task that contains description of event,
 * as well as start and end date/time.
 */
public class EventTask extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Public constructor for event.
     *
     * @param description Details of event.
     * @param start Start date and time of event.
     * @param end End date and time of event.
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description, "E");
        this.start = start;
        this.end = end;
    }

    /**
     * Obtains string representation of event.
     *
     * @return Information of event.
     */
    @Override
    public String toString() {
        return super.toString()
                + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma")) + ")";
    }
}
