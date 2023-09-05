package task;

import java.time.LocalDateTime;

/**
 * Deadline class is used for tasks that have stipulated time to finish.
 */
public class Deadline extends Task{
    /**
     * The By.
     */
    protected LocalDateTime by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param by          the by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, this.by);
    }
}
