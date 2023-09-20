package ruiz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task that has a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime taskDeadline;
    protected String location;

    /**
     * A constructor for the public Deadline class.
     *
     * @param description the description of the deadline.
     * @param by          the deadline of the task.
     */
    public Deadline(String description, String by, String location) throws DateTimeParseException {
        super(description, location);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.taskDeadline = LocalDateTime.parse(by, formatter);
        this.location = location;
    }

    /**
     * This method converts the value of the deadline into a String type.
     *
     * @return the String representation of the deadline with its type, completion status and completion time.
     */
    @Override
    public String formatSaveTaskString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D"
                + super.formatSaveTaskString()
                + " | "
                + this.taskDeadline.format(formatter)
                + " | "
                + location;
    }

    /**
     * This method converts the value of the deadline into a String type.
     *
     * @return the String representation of the deadline with its type, completion status and completion time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]"
                + super.toString()
                + " (by: "
                + this.taskDeadline.format(formatter)
                + ")"
                + " at: "
                + location;
    }
}
