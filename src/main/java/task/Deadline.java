package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

enum Priority {
    HIGH, MEDIUM, LOW
}

public class Deadline extends Task {
    protected LocalDate by;
    protected Priority priority;
    protected boolean isDone;

    public Deadline(String description, LocalDate by, Priority priority, boolean isDone) {
        super(description);
        this.by = by;
        this.priority = priority;
        this.isDone = isDone;
    }

    public Deadline(String description, Date date) {
        super(description);
    }

    public Deadline(String description, String s) {
        super(description);
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ") (priority: " + priority + ")";
    }

    @Override
    public String toSaveString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by + " | " + priority.name();
    }
}
