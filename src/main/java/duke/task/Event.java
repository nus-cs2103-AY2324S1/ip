package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // Constants
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Fields
    LocalDate start;
    LocalDate end;

    //Constructor for duke.task.Event
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start.format(FORMAT), end.format(FORMAT));
    }
}
