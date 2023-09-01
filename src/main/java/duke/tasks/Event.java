package duke.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an event that consists of a description and start and end dates.
 */
public class Event extends Task implements Serializable {
    private static final long serialVersionUID = 4L;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(), startDate, endDate);
    }
}
