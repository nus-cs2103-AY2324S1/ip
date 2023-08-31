package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

enum Priority {
    HIGH, MEDIUM, LOW
}

public class Deadline extends Task {
    protected LocalDate by;
    protected Priority priority;

    public Deadline(String description, LocalDate by, Priority priority) {
        super(description);
        this.by = by;
        this.priority = priority;
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
