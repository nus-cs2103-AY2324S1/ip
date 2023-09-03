package Remy.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;

    /**
     * Constructs new Deadline object with a description and a due date.
     * Due date is stored in dd MMM yyyy format (e.g. 15 Oct 2023).
     * @param description Name of Deadline.
     * @param by Due date of Deadline. Should be in yyyy-MM-dd format.
     * @throws DateTimeParseException if given due date is in incorrect format.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);

        LocalDate temp = LocalDate.parse(by);
        this.by = temp.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Returns String representation of Deadline.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
