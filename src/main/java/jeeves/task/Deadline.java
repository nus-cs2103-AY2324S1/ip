package jeeves.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format(this.id + ". [D]" + super.toString() + " (by: " + by + ")");
    }
}
