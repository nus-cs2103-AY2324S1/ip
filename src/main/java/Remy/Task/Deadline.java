package remy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Deadline task that contains a description and a due date.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs new Deadline object with a description, due date, and priority.
     * Due date is stored in dd MMM yyyy format (e.g. 15 Oct 2023).
     *
     * @param description Description of deadline.
     * @param by          Due date of deadline.
     * @param priority    Priority of deadline. May be null.
     * @throws DateTimeParseException if Due date is in incorrect format (i.e. not yyyy-mm-dd)
     */
    public Deadline(String description, String by, String priority) throws DateTimeParseException {
        super(description, priority);

        LocalDate temp = LocalDate.parse(by);
        this.by = temp.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
