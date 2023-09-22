package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a task that has to completed by a certain point in time.
 */

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor of Deadline class.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the deadline of this task in a specified String format.
     * @return the deadline of this task.
     */
    public String getBy() {
        return this.by.toString();
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
