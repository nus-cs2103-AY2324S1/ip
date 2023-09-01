package ballsorting;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Creates a new instance of Event.
     * @param description Summary of Event.
     * @param start Start date and time of Event.
     * @param end End date and time of Event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a String representation of the Event.
     * @return String summary of Event.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.start.format(Ballsorter.outputFormatter)
                + " to: " + this.end.format(Ballsorter.outputFormatter) + ")";
    }

    /**
     * Returns a String representation that is stored in storage.
     * @return String summary that is stored.
     */
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "E|" + stat + "|" + this.description + "|"
                + this.start.format(Ballsorter.inputFormatter) + "|" + this.end.format(Ballsorter.inputFormatter);
    }
}
