package duke.task;

import duke.exception.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task which holds the date from and to.
 */
public class Event extends Task {

    /**
     * The start time of the event task.
     */
    private LocalDate from;

    /**
     * The end time of the event task.
     */
    private LocalDate to;

    /**
     * Constructs an duke.task.Event task
     *
     * @param description The description of the task.
     * @param from        The start time of the task.
     * @param to          The end time of the task.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.TaskType = TaskType.EVENT;
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeException e) {
            throw new DukeException("Wrong date format. Please Use format YYYY-MM-DD");
        }
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return this.isDone ? "[E][X] " + this.description + " (from: " + formatDate(from) + " to: " + formatDate(to) + ")"
                : "[E][ ] " + this.description + " (from: " + formatDate(from) + " to: " + formatDate(to) + ")";
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
        return "E" + "|" + isDone + "|" + this.description + "|" + from + "|" + to;
    }

    /**
     * Determine if the date is between the event date.
     *
     * @param date to compare.
     * @return true if the date is between the event date.
     */
    @Override
    public boolean onDate(LocalDate date) {
        return date.isAfter(this.from) && date.isBefore(this.to);
    }
}
