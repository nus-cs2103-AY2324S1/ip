public class DeadLine extends Task {
    protected String by;

    public DeadLine(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        // return "[D]" + (isDone ? "[X]" : "[ ]") + super.toString() + " (by: " + by + ")";
        return getTask() + getStatusIcon() + " " + description + " (by: " + by + ")";
    }
}
