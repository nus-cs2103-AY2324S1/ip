package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline.
 */
public class Deadline extends Task {
    private final LocalDate endDate;
    private final LocalTime endTime;

    /**
     * Creates a deadline task instance.
     *
     * @param description Description of the task.
     * @param date duke.task.Deadline date of the task.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.endDate = date;
        this.endTime = null;
    }

    /**
     * Creates a deadline task instance with a specific time.
     *
     * @param description Description of the task.
     * @param date duke.task.Deadline date of the task.
     * @param time duke.task.Deadline time of the task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.endDate = date;
        this.endTime = time;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToString() {
        return "[D] " + super.convertToString() + " (by: "
                + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + this.convertEndTimeToString() + ")";
    }

    private String convertEndTimeToString() {
        return (this.endTime != null)
                ? " " + this.endTime.format(DateTimeFormatter.ofPattern("h:mma"))
                : "";
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String convertToStringInFile() {
        return "[D] /" + super.convertToStringInFile() + " / " + this.endDate
                + this.convertEndTimeToStringInFile();
    }

    private String convertEndTimeToStringInFile() {
        return this.endTime != null
                ? " / " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }
}
