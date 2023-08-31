package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task with a deadline.
 * Inherits from duke.tasks.Task class.
 * @author Wu Jingya
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;

    /**
     * Creates a Deadline duke.tasks.Task with the specified description and deadline
     * @param description The task's description
     * @param by The task's deadline
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, LocalDateTime by, Boolean done) {
        super(description, done);
        this.by = by;
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + by;
    }
}
