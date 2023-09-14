package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task {

    /** The deadline of the task */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Checks if another task is a duplicate of this deadline.
     *
     * @param otherTask The other task to compare with.
     * @return true if the tasks are duplicates, false otherwise.
     */
    @Override
    public boolean isDuplicate(Task otherTask) {
        if (otherTask instanceof Deadline) {
            Deadline otherDeadline = (Deadline) otherTask;
            boolean isSameEndTime = areEqualIgnoringSeconds(this.getBy(), otherDeadline.getBy());
            boolean isSameDescription = this.getDescription().equals(otherDeadline.getDescription());
            return isSameEndTime && isSameDescription;
        }
        return false;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a formatted string representation of the Deadline object.
     *
     * @return A formatted string representation of the Deadline object.
     */
    @Override
    public String toFormattedString() {
        return "D | " + (isDone ? "1" : "0") + " | "
                + description + " | " + by;
    }
}
