package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor of the DeadLine class.
     * @param description Description of the Task.
     * @param by The deadline of the Task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of the Task that is to be stored in the specified file.
     * @return The String representation of the Task that is to be stored in the specified file.
     */
    @Override
    public String toWrite() {
        return "[D]" + super.toWrite() + " (by: " + this.by.toString() + ")";
    }

    /**
     * Returns the String representation of the Task that is printed.
     * @return The String representation of the Task that is printed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
