package sana;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type deadline.
 */
public class Deadline extends Task {

    private LocalDate dueDate;

    /**
     * Constructs instance of a task of type deadline.
     * @param description description of task.
     * @param dueDate task deadline.
     * @param isDone indicator of whether the task is done or not.
     */
    public Deadline(String description, LocalDate dueDate, boolean isDone) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Returns string representation of deadline task.
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a formatted task string for saving to files.
     *
     * @return formatted task string for saving to files.
     */
    @Override
    public String formatTask() {
        return "D" + super.formatTask() + " | " + dueDate;
    }
}
