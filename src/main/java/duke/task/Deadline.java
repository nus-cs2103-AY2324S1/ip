package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline in the Duke application.
 */
public class Deadline extends Task {
    protected LocalDateTime byDate;

    /**
     * Constructs a Deadline object with the specified description and due date.
     *
     * @param description The description of the task.
     * @param byDate The due date and time of the task.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns a string representation of the Deadline task for display purposes.
     *
     * @return A formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for writing to a file.
     * The date and time are formatted differently for file storage.
     *
     * @return A formatted string representation of the Deadline task for file storage.
     */
    @Override
    public String writeFileString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
