public class Deadline extends Task {
    private final String by;

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    Deadline(String taskName, boolean isMarked, String by) {
        super(taskName, isMarked);
        this.by = by;
    }
}
