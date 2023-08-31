import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task object with a deadline
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Create task based on description and deadline
     *
     * @param description The description of the task
     * @param by Deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Get the description of the deadline
     *
     * @return Description of task with its deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " +
                LocalDate.parse(by).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
