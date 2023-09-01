package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class Event extends class Task which consist of variable for Event.
 */
public class Events extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * constructor for class Events.
     *
     * @param description string for deadline's description
     * @param startDateTime LocalDateTime for start date of the event
     */
    public Events(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + startDateTime.format(formatter) + " to: " + endDateTime.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startStr = startDateTime.format(formatter);
        String endStr = endDateTime.format(formatter);
        String done = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", done, description, startStr, endStr);
    }
}
