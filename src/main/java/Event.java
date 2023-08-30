import java.time.LocalDateTime;

/**
 * This is the Event class, a child class of Task class
 * @author Selwyn
 */
public class Event extends Task{
    /**
     * Field representing the start date & time of the event
     */
    protected LocalDateTime startDateTime;

    /**
     * Field representing the end date & time of the event
     */
    protected LocalDateTime endDateTime;

    /**
     * Constructor for an Event task
     *
     * @param detail
     */
    public Event(String detail, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(detail);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * This method returns the string representation of an event task
     * @return String representation of an event task
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + super.getDisplayDateTime(this.startDateTime) + " to: "
                + super.getDisplayDateTime(this.endDateTime) + ")";
    }
}
