package duke.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents an event that consists of a description and start and end dates.
 */
public class Event extends Task implements Serializable {
    private static final long serialVersionUID = 4L;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(), startTime, endTime);
    }
}
