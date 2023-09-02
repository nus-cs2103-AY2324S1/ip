package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that starts and ends at a specific time.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /**
     * Initialises the event task with the given description, start time and end
     * time.
     * 
     * @param description The description of the event task.
     * @param startTime   The start time of the event task.
     * @param endTime     The end time of the event task.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, "E");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startTime.format(formatter) + " to " + endTime.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + startTime + " - " + endTime;
    }
}