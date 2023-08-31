package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon() == "X") {
            mark = 1;
        } else {
            mark = 0;
        }
        return "E | " + mark + " | " + super.writeToFile() + " | " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " -> " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
