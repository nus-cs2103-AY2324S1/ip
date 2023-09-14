package sae.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Deadline class represents a sae.task with a specific deadline.
 * It extends the sae.task.Task class and adds a 'by' field to store the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline sae.task with the given description and deadline.
     *
     * @param description The description of the sae.task.
     * @param by          The deadline of the sae.task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString method to format the Deadline sae.task's details.
     *
     * @return A formatted string representing the Deadline sae.task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    public String toFileString() {
        String completionStatus = isDone ? "1" : "0";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy ha");
        String formattedBy = by.format(formatter);

        return String.format("%s | %s | %s | %s", "D", completionStatus, description.trim(), formattedBy);
    }
}
