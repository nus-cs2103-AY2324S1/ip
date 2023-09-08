package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String PRINT_FORMAT = "[E]%s %s (from: %s to: %s)";
    private static final String STORE_FORMAT = "[E] | %s %s | %s | %s";
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String description, LocalDateTime from, LocalDateTime to) {

        super(description, TaskType.EVENT);
        this.start = from;
        this.end = to;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
    @Override
    public String saveString() {
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim(), start, end);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription(),
                start.format(dtFormat), end.format(dtFormat));
    }
}


