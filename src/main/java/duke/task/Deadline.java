package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline.
 */
public class Deadline extends Task {
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Creates a deadline task instance.
     *
     * @param description Description of the task.
     * @param date duke.task.Deadline date of the task.
     */
    public Deadline(String description, LocalDate date, int rank) {
        super(description, rank);
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
    public Deadline(String description, LocalDate date, LocalTime time, int rank) {
        super(description, rank);
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
        assert this.endDate != null : "End date should not be empty";
        return "[D] " + super.convertToString() + " (by:"
                + convertEndDateToString() + convertEndTimeToString() + ")";
    }

    private String convertEndDateToString() {
        return (this.endDate != null)
                ? " " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                : "";
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
        assert this.endDate != null : "End date should not be empty";
        return "[D] /" + super.convertToStringInFile() + " / " + this.convertEndDateToStringInFile()
                + this.convertEndTimeToStringInFile();
    }

    private String convertEndDateToStringInFile() {
        return this.endDate.toString();
    }

    private String convertEndTimeToStringInFile() {
        return this.endTime != null
                ? " / " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                : "";
    }
}
