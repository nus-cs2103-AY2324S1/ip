package chatter.task;

import java.time.LocalDate;

/**
 * Represents a task that has a deadline.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Deadline extends Task {
    /** A string indicating deadline of task in localDate form. */
    protected LocalDate by;
    /** A string indicating deadline of task not in local date format. */
    protected String alternateBy;

    /**
     * A constructor to initialize the chatter.task.Deadline class.
     *
     * @param description Description of the task.
     * @param by chatter.task.Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);

        try {
            this.by = LocalDate.parse(by);
            this.alternateBy = "";
        } catch (Exception e) {
            this.alternateBy = by;
        }

    }

    /**
     * Returns the string representation of the chatter.task.Deadline that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the chatter.task.Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.alternateBy + convertDateToString(this.by) + ")";
    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public String toStorageString() {
        return "D, " + this.isDone + ", " + this.description + ", " + this.alternateBy
                + convertDateToStorageString(this.by);
    }
}
