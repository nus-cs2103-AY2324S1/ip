public class Deadline extends Task {

    protected String by;
    protected boolean isDone = false;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveString(){
        String divider = " | ";
        return "D" + divider + super.toSaveString() + divider + this.by;
    }
}
