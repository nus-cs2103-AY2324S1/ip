package task;
import java.time.LocalDateTime;

/**
 * This class encapsulates an Event
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Instantiates a new Event.
     *
     * @param description the Event description
     * @param from        the from time yyyy-MM-dd HH:mm
     * @param to          the to time yyyy-MM-dd HH:mm
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = formatDateAndTime(from);
        this.toDateTime = formatDateAndTime(to);
    }
    /**
     * Instantiates a new Event.
     *
     * @param status     the status
     * @param description the Event description
     * @param from       the from time yyyy-MM-dd HH:mm
     * @param to         the to time yyyy-MM-dd HH:mm
     */
    public Event(int status, String description, String from, String to) {
        super(description, status != 0); //if 0, return false, else return true
        this.from = from;
        this.to = to;
        this.fromDateTime = formatDateAndTime(from);
        this.toDateTime = formatDateAndTime(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storeToDiskFormat() {
        return "E" + "|" + this.diskStatus() + "|" + this.getDescription() + "|" + this.from + "|" + this.to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: "
                + printDateTimeFormat(this.fromDateTime) + " to: " + printDateTimeFormat(this.toDateTime) + ")";
    }

}
