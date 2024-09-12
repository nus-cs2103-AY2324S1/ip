package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initializes a deadline task with the given description and date in String.
     *
     * @param description Description of the deadline task.
     * @param by          Due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.by = LocalDate.parse(by);
    }

    /**
     * Initializes a deadline task with the given description and date in LocalDate.
     *
     * @param description Description of the deadline task.
     * @param by          Due date of the deadline task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        assert by != null : "Due date should not be null";
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns a string representation of the deadline task.
     * example: [D][X] return book (by: Aug 6 2021)
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
