public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String fileString() {
        return "D | " + super.fileDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.taskDescription() + " (by: " + by + ")";
    }
}