package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a task with an end date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor method for Deadline.
     * @param description Description of the Deadline.
     * @param by Due date of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String printDesc() {
        try {
            String byString = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
            return "[D]" + super.printDesc() + " (by: " + byString + ")";
        } catch (IllegalArgumentException e) {
            return "     Please key in the dates in the format of YYYY-MM-ddThh:mm:ss";
        }
    }

    @Override
    public String getDescription() {
        return "D~" + super.getDescription() + "~" + this.by;
    }

}
