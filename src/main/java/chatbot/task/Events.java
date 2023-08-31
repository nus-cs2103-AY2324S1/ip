package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Events(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E][" + getStatusIcon() + "] " + description
                + " (from: " + startDateTime.format(formatter) + " to: " + endDateTime.format(formatter) + ")";
    }

    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startStr = startDateTime.format(formatter);
        String endStr = endDateTime.format(formatter);
        String done = isDone ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", done, description, startStr, endStr);
    }
}
