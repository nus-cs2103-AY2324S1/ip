package services.tasklist.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String startTime, String endTime) throws IllegalArgumentException {
        super(description);
        try {
            this.startTime = LocalDateTime.parse(startTime, inputFormatter);
            this.endTime = LocalDateTime.parse(endTime, inputFormatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("event");
        }
    }

    @Override
    public String encode() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(outputFormatter)
                + " to: " + endTime.format(outputFormatter) + ")";
    }
}
