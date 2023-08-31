package harvard;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    /**
     * The deadline of the task.
     */
    protected LocalDateTime by;
    /**
     * The type of the task.
     */
    protected String type = "D";
    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */

    public Deadline(String description, String by) throws DukeException {
        super(description, "D");
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Please enter a valid date and time in the format dd/MM/yyyy HHmm");
        }
    }

    public Deadline (String description, LocalDateTime by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String toFileString() {
        return this.type + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.by;
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedBy = by.format(formatter);
        return "[" + type + "]" + super.toString() + " (by: " + formattedBy + ")";
    }
}
