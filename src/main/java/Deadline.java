public class Deadline extends Task {

    protected String by;
    public boolean isDone;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.isDone = false;

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}