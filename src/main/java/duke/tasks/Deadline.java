package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.components.Status;

/**
 * Encapsulates a Deadline. Contains the task description, completion status
 * and date of deadline.
 */
public class Deadline extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Class constructor for Deadline.
     *
     * @param status either DONE or NOT_DONE.
     * @param task   task description.
     * @param date   deadline of the task.
     */
    public Deadline(Status status, String task, LocalDateTime date) {
        super(status, task);
        this.date = date;
    }

    /**
     * Returns true if the deadline is within the desired period.
     *
     * @param start start date to compare to.
     * @param end   end date to compare to.
     * @return true if start is before the input date.
     */
    public boolean isWithin(LocalDateTime start, LocalDateTime end) {
        boolean isEqualStart = this.date.isEqual(start);
        boolean isAfterStart = this.date.isAfter(start);
        boolean isGreaterEqual = isEqualStart || isAfterStart;
        boolean isBeforeEnd = this.date.isBefore(end);
        return isGreaterEqual && isBeforeEnd;
    }

    /**
     * Converts Deadline to the correct string format to write to data file.
     *
     * @return string to write to data file.
     */
    @Override
    public String convertTask() {
        return "D | " + super.getStatus() + " | " + super.getTask()
                + " | " + this.date.format(formatter);
    }

    /**
     * Returns string representation of a Deadline object.
     *
     * @return string Deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.date.format(formatter) + ")";
    }
}
