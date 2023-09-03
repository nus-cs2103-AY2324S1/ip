package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that need to be done before a specific date/time.
 *
 * @author Sebastian Tay
 */
public class Deadline extends Task{
    final static String SYMBOL = "D";
    protected String by;

    protected LocalDateTime deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.deadline = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }


    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HHmm")) + "H)";
    }

    @Override
    public String convertToStorageForm() {
        final String SEPARATOR = "::";
        final String status = isDone() ? "1" : "0";

        //D::0::return book::June 6th
        return SYMBOL + SEPARATOR + status + SEPARATOR + getDescription() + SEPARATOR + this.by;
    }
}
