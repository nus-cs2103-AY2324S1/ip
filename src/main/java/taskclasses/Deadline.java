package taskclasses;

import extensions.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type "Deadline" with a specific due date.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Constructs a Deadline task with the given description, completion status, and due date.
     *
     * @param description The description of the deadline task.
     * @param isDone      The completion status of the task.
     * @param by          The due date of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate by, Tag tag) {
        super(description, isDone, tag);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String formatToFile() {
        return "D | " + super.formatToFile() + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
