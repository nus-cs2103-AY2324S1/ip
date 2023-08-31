package duke.tasks;

import java.time.LocalDateTime;
/**
 * duke.tasks.Deadline class representing a task and description
 */
public class Deadline extends Task {

    protected LocalDateTime dueBy;

    /**
     * Constructor for the deadline class
     * @param description Description of the task
     * @param dueBy deadline of the task
     */
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = parseDateTime(dueBy.split(" ", 2)[1]);
    }

    /**
     * String representation of the deadline task
     * @return Information of deadline
     */
    @Override
    public String toString() {
        String ret = "[D] " + super.toString() + " (by: " + printDateTime(this.dueBy) + ")" ;

        return ret;
    }
}
