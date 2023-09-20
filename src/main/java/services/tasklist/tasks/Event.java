package services.tasklist.tasks;

import services.bizerrors.InvalidArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start time and end time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String startTime, String endTime) throws InvalidArgumentException {
        super(description);
        try {
            this.startTime = LocalDateTime.parse(startTime, inputFormatter);
            this.endTime = LocalDateTime.parse(endTime, inputFormatter);
        } catch (Exception e) {
            throw new InvalidArgumentException("event");
        }
    }

    @Override
    public String encode() {
        return "E | " + (isDone ? "1" : "0") + " | " + showAllTags() + " | " + description + " | "
                + startTime + " | " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "\n(from: " + startTime.format(outputFormatter)
                + " to: " + endTime.format(outputFormatter) + ")";
    }
}
