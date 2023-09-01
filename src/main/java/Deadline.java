public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toStorageString() {
        String done = this.isDone ? "1" : "0";
        return String.join("|","deadline", done, this.description, this.by);
    }
}

