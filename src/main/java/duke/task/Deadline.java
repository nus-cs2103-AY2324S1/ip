package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline in the Duke application.
 */
public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    /**
     * Constructs a Deadline object with the specified description and deadline date/time.
     *
     * @param description The description of the deadline task.
     * @param byDateTime  The deadline date and time.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return A formatted string displaying the deadline task's details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    /**
     * Generates a formatted string representation of the deadline task for saving.
     *
     * @return A formatted string suitable for saving in a data file.
     */
    @Override
    public String toSaveLine() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0,
                             description, byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
