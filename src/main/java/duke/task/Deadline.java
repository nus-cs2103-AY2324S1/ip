package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class to represent deadline tasks
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs deadline with the given description
     *
     * @param description the given description of the deadline
     * @param isDone      whether the deadline is done
     * @param by          the date by which the deadline is
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the do by date
     *
     * @return String representing do by date
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns String representing the deadline
     *
     * @return String representing deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}