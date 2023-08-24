public class Deadline extends Task {

    protected String by;
    protected boolean isDone = false;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
