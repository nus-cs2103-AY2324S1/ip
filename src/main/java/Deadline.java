public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + this.by;
    }

    @Override
    public String toString() {
        return super.toString() + "(by:" + this.by + ")";
    }
}
