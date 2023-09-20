package duke.data.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Deadline class is a child class of Task that represents tasks
 * with a description and a specific deadline date.
 */
public class Deadline extends Task {
    /** The date of the deadline. */
    protected Date by;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The date of the deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toWrite() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "D | " + super.toWrite() + " | " + formatter.format(by) + "\n";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + formatter.format(by) + ")";
    }
}
