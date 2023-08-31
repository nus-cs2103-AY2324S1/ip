package duke.data.task;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    /** Date of deadline */
    protected Date by;

    /**
     * Constructor to initialize Deadline.
     *
     * @param description Description of the deadline task.
     * @param by Date of deadline.
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
