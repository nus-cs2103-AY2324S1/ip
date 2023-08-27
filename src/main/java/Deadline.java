public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String type() {
        return "[D]";
    }

    @Override
    public String toString() {
        return type() + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}