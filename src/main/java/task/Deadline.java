package task;

public class Deadline extends Task {

    /** deadline type String */
    protected String by;
    /**
     * Initialize task.Task object with task name and task is not done.
     *
     * @param description type String;
     * @param by type String;
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileFormat() {
        return ("D|" + super.fileFormat() + "|" + this.by);
    }
}
