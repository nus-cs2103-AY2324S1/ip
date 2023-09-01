public class Deadline extends Task { // inheritance
    protected String by;

    public Deadline(String description, String by) { // constructor
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + "|" + by;
    }
}
