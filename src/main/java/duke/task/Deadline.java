package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task which holds the date which should be completed by.
 */
public class Deadline extends Task {

    /**
     * The due date of the deadline task.
     */
    private LocalDate date;

    /**
     * Constructs an unmarked duke.task.Deadline task
     * with the given description and due date.
     *
     * @param description The description of the task.
     * @param date        The due date of the task.
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);
        this.TaskType = TaskType.DEADLINE;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeException e) {
            throw new DukeException("Wrong date format. Please Use format YYYY-MM-DD.");
        }
    }

    /**
     * Returns a string representation of the deadline task.
     * Includes its completion status, description, and due date.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return isDone ? "[D][X] " + this.description + " (by: " + formatDate(this.date) + ")"
                : "[D][ ] " + this.description + " (by: " + formatDate(this.date) + ")";
    }

    /**
     * Change the Dates to a different format.
     */
    private String formatDate(LocalDate date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        return date.format(format);
    }

    /**
     * Used for easier readability during
     * storing into the file.
     *
     * @return the raw version of the task for storing.
     */
    @Override
    public String getRaw() {
        return "D" + "|" + isDone + "|" + this.description + "|" + date;
    }


    /**
     * Determine if the task is on a specific date.
     *
     * @param date to compare.
     * @return true if the task is at a date.
     */
    @Override
    public boolean onDate(LocalDate date) {
        return this.date.equals(date);
    }
}
