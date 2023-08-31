package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initializes a new Deadline object with the specified description and deadline.
     *
     * @param name The description of the deadline task.
     * @param by          The deadline of the task.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return The type of the task (in this case, "D" for Deadline).
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Retrieves the description of the task, along with the formatted deadline.
     *
     * @return The formatted description of the task including the deadline.
     */
    @Override
    public String getDescription() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return super.toString() + " | " + formattedBy ;
    }

    /**
     * Retrieves the formatted deadline.
     *
     * @return The formatted deadline in the format "d MMM yyyy h:mma".
     */
    public String getByFormatted() {
        return this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Generates the status and task name representation of the deadline task.
     *
     * @return A string representation of the deadline task's status, name, and deadline.
     */
    @Override
    public String statusAndTask() {
        return "[D]" + statusString() + " " + super.toString() + " (by: " + getByFormatted() + ")";
    }

}
