package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to maintain the deadline tasks entered by user.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    protected LocalTime endTime;

    /**
     * Constructor for Deadline class.
     * @param list List to add
     * @param deadline deadline
     * @param endTime end time of deadline task
     * @param type enum type
     */

    public Deadline(String list, String deadline, String endTime, TaskType type) {
        super(list, type);
        this.deadline = LocalDate.parse(deadline);
        this.endTime = LocalTime.parse(endTime);
    }

    /**
     * Marks the task as completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful marking of the task.
     * @throws DukeException If the task has already been marked as done.
     */
    @Override
    public String setMarked() throws DukeException {
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    /**
     * Marks the task as not completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful unmarking of the task.
     * @throws DukeException If the task has already been marked as not done.
     */
    @Override
    public String setUnmarked() throws DukeException {
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }

    @Override
    public String toString() {
        return super.toString()
                + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.endTime + ")";
    }

}
