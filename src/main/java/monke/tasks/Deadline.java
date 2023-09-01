package monke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline in the Monke application.
 * It extends the Task class.
 */
public class Deadline extends Task {
    /** The deadline date and time of the task. */
    private LocalDateTime date;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param date The deadline date and time.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes its status icon, description, and deadline.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")));
    }

    /**
     * Formats the Deadline task data for saving to a file.
     *
     * @return A formatted string representing the Deadline task data.
     */
    @Override
    public String formatData() {
        return String.format("D | %d | %s | %s\n", this.isDone ? 1 : 0,
                this.description, this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
