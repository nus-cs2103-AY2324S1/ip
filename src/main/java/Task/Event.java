package task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class is used for tasks that are held specific date and time.
 */
public class Event extends Task {
    /**
     * The From.
     */
    private String from;
    private String to;
    private LocalDateTime fromDateTime;
    /**
     * The To.
     */
    private LocalTime toTime;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.fromDateTime = LocalDateTime.parse(from, newFormat);
        this.toTime = LocalTime.parse(to);
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon() + "(from: " + printDateTimeFormat(fromDateTime) + " to: " + toTime + ")";
    }

    @Override
    public String toString() {
        return String.format("E | %d | %s | %s | %s", super.isDone ? 1 : 0, super.description, this.from, this.to);
    }
}
