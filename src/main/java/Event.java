import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a subclass of Task which defines an event task.
 */
public class Event extends Task {
    /** A String containing start of event. */
    private LocalDateTime from;
    /** A String containing end of event. */
    private LocalDateTime to;

    /**
     * A constructor of event
     * @param description Name of event
     * @param from Start of event
     * @param to End of event
     * @throws HelpBuddyException If description, start and end time of event is empty.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws HelpBuddyException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a event cannot be empty.\n");
        } else if (from == null) {
            throw new HelpBuddyException("Please enter a start time of event.\n");
        } else if (to == null) {
            throw new HelpBuddyException("Please enter an end time of event.\n");
        }
    }

    /**
     *
     * @return String representation of event object
     */
    @Override
    public String toString() {
        String startTime = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String endTime = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + "(from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.from + " to " + this.to);
    }

}
