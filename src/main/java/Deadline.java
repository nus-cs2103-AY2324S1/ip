/**
 * A Deadline is a task which needs to be completed by a certain date. As a result, it keeps track
 * of when it must be completed by.
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String composeToFileString() {
        return String.format("D|%s|%s", super.composeToFileString(), this.by);
    }
}
