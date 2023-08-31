package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime byDateTime;
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.byDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.by = byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}