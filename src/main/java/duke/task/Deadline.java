package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a duke.task.Task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for a duke.task.Deadline instance.
     *
     * @param description The description of the underlying task.
     * @param by          The date by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Provides the string representation of the duke.task.Deadline instance.
     *
     * @return A string with the relevant information of the duke.task.Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")));
    }

    /**
     * Get a formatted string of the duke.task.Deadline task to add to the save file.
     *
     * @return A formatted string with the relevant information for the save file.
     */
    @Override
    public String getSaveString() {
        return String.format("D | %d | %s | %s",
                this.isDone ? 1 : 0,
                this.description,
                this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}
