import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    /** LocalDate deadline for Deadline. */
    protected LocalDate by;

    /** Constructor for Deadline.
     *
     * @param description Description of task.
     * @param by Deadline for Deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /** toString method for Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
