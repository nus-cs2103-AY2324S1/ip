import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the starting time of the event.
     * 
     * @return The starting time of the event.
     */
    public LocalDateTime getStartTime() {
        return from;
    }

    /**
     * Returns the ending time of the event.
     * 
     * @return The ending time of the event.
     */
    public LocalDateTime getEndTime() {
        return to;
    }

    /**
     * Returns a string representation of Event.
     *
     * @return A string representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")),
                to.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")));
    }
}
