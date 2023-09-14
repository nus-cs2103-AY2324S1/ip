package duke.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a deadline that consists of a description and a due date.
 */
public class Deadline extends Task implements Serializable {
    private static final long serialVersionUID = 3L;
    protected LocalDateTime by;

    /**
     * Constructor for creating a task with a deadline.
     *
     * @param description A description of the deadline.
     * @param by          The due date of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), by);
    }
}
