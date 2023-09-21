package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and adds functionality for handling deadlines.
 */
public class Deadline extends Task {
    protected LocalDateTime date;

    /**
     * Constructs a Deadline object with the specified task description and deadline.
     *
     * @param description The description of the task to be added.
     * @param date The deadline of the task as a LocalDateTime object.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string containing task type, completion status, task description and deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
    }
}