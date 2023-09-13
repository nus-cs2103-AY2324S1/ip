package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task with a deadline.
 * Inherits from Task class.
 *
 * @author Wu Jingya
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;

    /**
     * Constructs a DeadlineTask object with the specified description and deadline.
     *
     * @param description The task's description.
     * @param by The task's deadline.
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a DeadlineTask object with the specified description, deadline and completion status.
     *
     * @param description The task's description.
     * @param by The task's deadline.
     * @param isDone Whether the task is completed.
     */
    public DeadlineTask(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        String byFormatted = by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + by;
    }

    //For testing purposes only
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DeadlineTask) {
            boolean hasSameDeadline = ((DeadlineTask) obj).by.equals(this.by);
            return super.equals(obj) && hasSameDeadline;
        }
        return false;
    }
}
