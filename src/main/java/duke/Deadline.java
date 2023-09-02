package duke;

import java.time.LocalDate;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;

    public Deadline(String desc, LocalDate by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String encode() {
        return String.format("D|%s /by %s", super.encode(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
