import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task object that contains a start and end time
 */
public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Create an Event Task based on description, start time and
     * end time
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Get description of the event
     *
     * @return description of task, start and end time
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " +
                LocalDate.parse(from).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                LocalDate.parse(to).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
