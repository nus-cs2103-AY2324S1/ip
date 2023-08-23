/**
 * Deadline class representing a task and description
 */
public class Deadline extends Task{

    protected String dueBy;

    /**
     * Constructor for the deadline class
     * @param description Description of the task
     * @param dueBy deadline of the task
     */
    public Deadline(String description, String dueBy) {
        super(description);
        this.dueBy = dueBy.split(" ", 2)[1];
    }

    /**
     * String representation of the deadline task
     * @return Information of deadline
     */
    @Override
    public String toString() {
        String ret = "[D] " + super.toString() + " (by: " + this.dueBy + ")" ;
        return ret;
    }
}
