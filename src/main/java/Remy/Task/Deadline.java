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
     * Constructs new Deadline object with a description and a due date.
     * Due date is stored in dd MMM yyyy format (e.g. 15 Oct 2023).
     * @param description Name of Deadline.
     * @param by Due date of Deadline. Should be in yyyy-mm-dd format.
     * @throws DateTimeParseException if given due date is in incorrect format.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);

        LocalDate temp = LocalDate.parse(by);
        this.by = temp.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Constructs new Deadline object with a description, due date, and priority.
     * Due date is stored in dd MMM yyyy format (e.g. 15 Oct 2023).
     * @param description
     * @param by
     * @param priority
     * @throws DateTimeParseException
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
