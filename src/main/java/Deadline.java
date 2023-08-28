public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusMessage() {
        return "[D]" + super.getStatusMessage() + " (by: " + this.by + ")";
    }

    @Override
    public String toFormattedString() {
        return String.format("D | %s", super.toFormattedString());
    }
}
