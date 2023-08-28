public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString().substring(3) + " (by: " + by + ")";
    }

    public String type() {
        return "D";
    }
    public String getBy() {
        return by;
    }


}