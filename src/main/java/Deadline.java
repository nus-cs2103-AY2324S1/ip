/**
 * The Deadline class containing tasks
 * with deadlines.
 * @author: Shishir
 **/
public class Deadline extends Task {

    /** The deadline of the task. **/
    private String deadline;

    /** The constructor
     * @param deadline The deadline of the task.
     * @param description The description of the task.
     * **/
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    /** The string representation of the task.
     * @return The string representation
     * **/
    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + deadline + ")";
    }
}
