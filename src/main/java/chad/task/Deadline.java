package chad.task;

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

    /**
     * Compares the Deadline object with a Task object, if Task object is
     * an Deadline object, compare the by dates of both objects.
     * @param that the object to be compared.
     * @return Positive integer if the other Task object is a Deadline with
     *     an earlier start date; Otherwise, a negative integer if the other
     *     Task is a Event, ToDo object or an Deadline object with late by date.
     */
    @Override
    public int compareTo(Task that) {
        if (that instanceof Deadline) {
            Deadline thatDeadline = (Deadline) that;
            return this.byDate.compareTo(thatDeadline.byDate);
        }
        return this.toString().charAt(1) - that.toString().charAt(1);
    }
}
