package task;

enum Priority {
    HIGH, MEDIUM, LOW
}

public class Deadline extends Task {
    protected String by;
    protected Priority priority;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.priority = null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ") (priority: " + priority + ")";
    }
}