package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with a given deadline.
 */
public class Deadline extends Task {
    protected LocalDate byDate;

    /**
     * Constructor for Deadline class.
     *
     * @param description The given description for the deadline.
     * @param byDate The given deadline.
     */
    public Deadline(String description, LocalDate byDate) {
        super(description);
        this.byDate = byDate;
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
        String deadline = "(by: " + this.byDate + ")";

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
