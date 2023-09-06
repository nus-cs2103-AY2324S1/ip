package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents task with a deadline that user is keeping track of.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructor for the class Deadline.
     * @param description Description of the task with deadline.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task with a deadline as a String formatted to be saved.
     * @return The task with a deadline as a String formatted to be saved.
     */
    @Override
    public String fileString() {
        return "D | " + super.fileDescription() + " | " + this.by;
    }

    /**
     * Returns the task with a deadline as a String.
     * @return The task with deadline as a String.
     */
    @Override
    public String toString() {
        return "[D]" + super.taskDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}