package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Creates a task with a deadline.
     *
     * @param desc the description of the task
     * @param by   the deadline of the task
     */
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
        return String.format("[D]%s (by: %s)",
                super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
