package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An extension of <code>Task</code>, a <code>Deadline</code> object keeps track of
 * a task that has a time-gated deadline (either a LocalDate or just a String)
 */

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected boolean isDate = true;

    /**
     * The class constructor.
     *
     * @param description Description of the Deadline task.
     * @param by Deadline of the task - either a LocalDate or a String
     */
    public Deadline(String description, String by) {
        super(description);

        //To treat the deadline as a date, the user must input it as either of the following formats.
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            this.date = LocalDate.parse(by, formatter1);
        } catch (DateTimeParseException eDate) {
            try {
                this.date = LocalDate.parse(by, formatter2);
            } catch (DateTimeParseException eDate2) {
                isDate = false;
                this.by = by;
            }
        }
    }

    /**
     * Gets the value of by, whether a <code>LocalDate</code> or <code>String</code>
     */
    public String getBy() {
        if (!isDate) {
            return this.by;
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter1);
    }

    /**
     * Returns the string form of the <code>Deadline</code> task, for writing to file.
     */
    @Override
    public String storedString() {
        return "D | " + super.storedString() + " | " + getBy();
    }

    /**
     * Returns the string form of the <code>Deadline</code> task, for display to user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}

