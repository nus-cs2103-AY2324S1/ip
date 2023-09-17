package yong.tasks;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task with a description and due date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime dueBy;

    /**
     * Constructor for the Deadline class.
     *
     * @param description Description of the task.
     * @param dueBy Deadline of the task in the format "yyyy-MM-dd HH:mm".
     */
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = parseDateTime(dueBy.split(" ", 2)[1]);
    }

    /**
     * String representation of the Deadline task.
     *
     * @return Information about the deadline.
     */
    @Override
    public String toString() {
        String ret = "[D] " + super.toString() + " (by: " + printDateTime(this.dueBy) + ")";
        return ret;
    }

    /**
     * Getter method for the dueBy date.
     *
     * @return LocalDateTime object representing the dueBy date and time.
     */
    public LocalDateTime getCompareDate() {
        return dueBy;
    }
}

