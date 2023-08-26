package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatLocalDateTime(by) + ")";
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, serializeLocalDateTime(by));
    }
}