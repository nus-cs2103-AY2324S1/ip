public class Deadline extends Task {
    protected String by;
    protected String type = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (by: " + getBy() + ")";
    }
}
