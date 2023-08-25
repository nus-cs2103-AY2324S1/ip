/**
 * The Deadline class extends Task and has an additional field
 * to store when the task must be completed by
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Deadline extends Task {

    protected String by;

    /** Constructor for Deadline */
    public Deadline(String done, String description, String by) {
        super(description, done);
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileRepresentation() {
        return ("D" + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + by + "\n");
    }
}

