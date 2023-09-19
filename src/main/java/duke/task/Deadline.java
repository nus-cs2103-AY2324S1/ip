package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    /**
     * Returns a formatted string representation of the Deadline task.
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    /**
     * Returns a formatted string representation of the Deadline task for file storage.
     *
     * @return The formatted string representation for file storage.
     */
    public String toStringFile() {
        return "D | " + super.toStringFile() + "/by " + by;
    }
    /**
     * Returns the type of the task.
     *
     * @return The type of the task ("Deadline").
     */
    @Override
    public String getType() {
        return "Deadline";
    }
}
