import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a deadline.
 *
 * @author Pearlynn
 */

public class Deadline extends Task{

    /**
     * The deadline of the task.
     */
    protected LocalDateTime by;

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Constructor for Deadline class.
     *
     * @param description The description of the deadline.
     * @param isDone The status of the deadline.
     * @param by The deadline of the deadline.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns the string representation of the deadline in the file.
     *
     * @return A string representation of the deadline in the file.
     */
    @Override
    public String taskStringify() {
        int status = super.isDone ? 1 : 0;
        String byFormat = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "D | " + status + " | " + super.description + " | " + byFormat;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return A string representation of the deadline.
     */
    @Override
    public String toString() {
        String byFormat = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        return "[D]" + super.toString() + " (by: " + byFormat + ")";
    }
}
