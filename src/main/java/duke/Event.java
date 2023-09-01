package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + this.endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon() == "X") {
            mark = 1;
        } else {
            mark = 0;
        }
        return "E | " + mark + " | " + super.writeToFile() + " | " + this.startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " -> " + this.endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
