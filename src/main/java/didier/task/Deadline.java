package didier.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a Task which needs to be completed by a certain date. As a result, it keeps track
 * of when it must be completed by.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * The constructor for the Deadline object. The deadline is marked as undone by default at the start.
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * The constructor for the Deadline object that allows the user to specify whether the Deadline is done
     * or undone initially.
     * @param description The description of the deadline task.
     * @param by The deadline of the task.
     * @param isDone Whether the task is done or not.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String composeToFileString() {
        return String.format("D|%s|%s", super.composeToFileString(), this.by.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.by.equals(deadline.by) && super.equals(obj);
        }
        return false;
    }
}
