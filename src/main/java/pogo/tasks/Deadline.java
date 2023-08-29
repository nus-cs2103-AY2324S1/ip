package pogo.tasks;

import pogo.tasks.exceptions.PogoInvalidTaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /**
     * Deadline of the task.
     */
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) throws PogoInvalidTaskException {
        super(description);
        if (by == null) {
            throw new PogoInvalidTaskException("Deadline cannot be empty");
        }
        this.by = by;
    }

    @Override
    public String getStatusMessage() {
        return "[D]" + super.getStatusMessage() + " (by: " + this.getDeadline() + ")";
    }


    /**
     * Returns the deadline of the task.
     *
     * @return Deadline of the task.
     */
    public String getDeadline() {
        return this.by.format(Task.DATETIME_FORMAT);
    }

    /**
     * Accepts a visitor that performs an action on the task.
     *
     * @param visitor Visitor to perform an action on the task.
     */
    public void accept(TaskVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Checks if the deadline for the task is between a specified date.
     */
    @Override
    public boolean isBetween(LocalDateTime start, LocalDateTime end) {
        return this.by.isAfter(start) && this.by.isBefore(end);
    }
}
