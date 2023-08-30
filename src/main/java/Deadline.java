public class Deadline extends Task {
    private String by;

    // Constructor for Deadline
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    // Constructor for Deadline with done status
    public Deadline(String name, String by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    // Gets string representation of the Deadline
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    // Gets the date that the Deadline is due by
    public String getBy() {
        return this.by;
    }
}