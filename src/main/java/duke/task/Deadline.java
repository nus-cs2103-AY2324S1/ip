package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    LocalDateTime date;

    protected String by;

    /**
     * Constructs a Deadline instance with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task in "yyyy-MM-dd HHmm" format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDateTime.parse(this.by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns the formatted deadline date.
     *
     * @return The formatted deadline date in "yyyy-MM-dd HHmm" format.
     */
    public String getBy() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string containing the task's details, including its description and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
