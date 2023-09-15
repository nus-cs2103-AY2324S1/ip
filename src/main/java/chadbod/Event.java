package chadbod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the ChadBod application, extending the Task class.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event class.
     *
     * @param description the description of the event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss")));
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", this.isDone ? 1 : 0, this.description, this.from, this.to);
    }
}
