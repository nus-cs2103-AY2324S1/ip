package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The Deadline class represents a task with a specific due date.
 * It inherits from the Task class and adds a due date field.
 */
public class Deadline extends Task {
    /**
     * The due date of the task.
     */
    private final LocalDate by;

    /**
     * Constructs a new Deadline object with the specified description, completion status, and due date.
     *
     * @param description The description of the Deadline task.
     * @param isDone      The completion status of the Deadline task.
     * @param deadline    The due date of the task.
     */
    public Deadline(String description, boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.by = deadline;
    }

    /**
     * Constructs a new Deadline object with the specified description and due date.
     * The deadline is initially marked as not done.
     *
     * @param description The description of the Deadline task.
     * @param deadline    The due date of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.by = deadline;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A formatted string indicating the task type, completion status, description, and due date.
     */
    @Override
    public String toString() {
        String b = this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return "[D] " + super.toString() + " (by: " + b + ")";
    }

    /**
     * Returns a formatted string for writing the Deadline task to a file.
     *
     * @return A formatted string containing task details for file storage.
     */
    @Override
    public String writeFile() {
        return "D | " + super.writeFile() + " | " + this.by;
    }
}
