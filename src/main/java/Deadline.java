public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
