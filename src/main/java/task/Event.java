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

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = formatDateAndTime(from);
        this.toDateTime = formatDateAndTime(to);
    }
    public Event(int status, String desciption, String from, String to) {
        super(desciption, status != 0);     //if 0, return false, else return true
        this.from = from;
        this.to = to;
        this.fromDateTime = formatDateAndTime(from);
        this.toDateTime = formatDateAndTime(to);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String storeToDiskFormat() {
        return "E" + "|" + this.getStatus() + "|" + this.getDescription() + "|" + this.from + "|" + this.to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + printDateTimeFormat(this.fromDateTime) + " to: " + printDateTimeFormat(this.toDateTime) + ")";
    }

}
