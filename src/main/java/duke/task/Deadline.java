package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a given deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime byDate;

    /**
     * Constructor for Deadline class.
     *
     * @param description The given description for the deadline.
     * @param byDate The given deadline.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;

        assert this.byDate != null : "byDate of the Deadline cannot be null";
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        String taskType = "[D]";
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String description = " " + super.description + " ";
        String deadline = "(by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";

        return taskType + status + description + deadline;
    }

    /**
     * Returns a string representation of the Deadline object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    @Override
    public String toDataFormatString() {
        String taskType = "D | ";
        String status = (super.isDone ? "1" : "0") + " | ";
        String description = super.description + " | ";
        String dates = this.byDate.toString();

        return taskType + status + description + dates;
    }
}
