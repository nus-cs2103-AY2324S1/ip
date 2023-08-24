public class Deadline extends Task {
    private String by;

    // Constructor for Deadline.
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    // Get string representation of the Deadline.
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}