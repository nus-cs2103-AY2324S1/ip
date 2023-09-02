public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String toFileString() {
        // Convert task to file format string
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

}
