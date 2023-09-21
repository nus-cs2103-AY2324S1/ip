package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public String getByString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + getByString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }
}
