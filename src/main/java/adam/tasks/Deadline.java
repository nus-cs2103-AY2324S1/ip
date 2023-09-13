package adam.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is used for creating deadline tasks that has a due date
 */
public class Deadline extends Task implements Serializable {
    protected LocalDate by;

    /**
     * Initializes the by when this task is due.
     *
     * @param text Text that gives the description of the task.
     * @param by By when this task is due.
     */
    public Deadline(String text, String by) {
        super(text);
        this.by = LocalDate.parse(by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
