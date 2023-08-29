import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Deadline extends Task {
    /** A string indicating deadline of task in localDate form. */
    protected LocalDate by;
    /** A string indicating deadline of task not in local date format. */
    protected String altBy;

    /**
     * A constructor to initialize the Deadline class.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (Exception e) {
            this.altBy = by;
        }

    }

    /**
     * Returns the string representation of the Deadline that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {

        if (this.by != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateString = this.by.format(formatter);
            return "[D]" + super.toString() + " (by: " + dateString + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.altBy + ")";
        }

    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public String toStorageString() {
        return "D, " + this.isDone + ", " + this.description + ", " + this.by;
    }
}
