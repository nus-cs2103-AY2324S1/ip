package duke.task;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Task class with a deadline date (by) */
public class Deadline extends Task {
    public LocalDate dateTime;

    /**
     * Initialize Deadline class.
     *
     * @param task Task.
     * @param dateTime Deadline for task.
     */
    public Deadline(@JsonProperty("task") String task, @JsonProperty("dateTime") LocalDate dateTime) {
        super(task);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
