package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline in the list of tasks.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the description of the deadline that is written to the data file.
     *
     * @return String representation of deadline stored in the data file.
     */
    @Override
    public String writeFile() {
        return "D | " + super.writeFile() + " | " + this.by;
    }

    /**
     * Return the description of the deadline that is printed to the user.
     *
     * @return String representation of deadline printed to the user.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                LocalDate.parse(this.by).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
