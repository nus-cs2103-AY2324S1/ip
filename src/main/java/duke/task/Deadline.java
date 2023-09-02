package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Deadline event in the Richie application
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructor for the Deadline task
     * @param description Description of the task
     * @param by The time that the task has to be completed by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertByToString() + ")";
    }

    /**
     * Returns the LocalDateTime date and time of when the task should be done by
     * @return LocalDateTime of the date and time of deadline of task
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Converts the LocalDateTime by into a String
     * @return String that represents the by
     */
    public String convertByToString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy 'at' hh:mm a"));
    }
}
