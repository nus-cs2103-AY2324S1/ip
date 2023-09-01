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
     * @param done Whether the task is completed.
     */
    public DeadlineTask(String description, LocalDateTime by, Boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + by;
    }

    // for testing purposes only
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DeadlineTask) {
            return super.equals(obj) &&
                    ((DeadlineTask) obj).by.equals(this.by);
        }
        return false;
    }
}
