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
        String status = "[" + (super.isDone ? "X" : " ") + "]";
        String deadline = "(by: " + this.byDate + ")";
        return "[D]" + status + " " + super.description + " " + deadline;
    }

    /**
     * Returns a string representation of the Deadline object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    @Override
    public String toDataFormatString() {
        String outputString = "D | ";
        outputString += (super.isDone ? "1" : "0") + " | ";
        outputString += super.description + " | ";
        outputString += this.byDate;
        return outputString;
    }
}
