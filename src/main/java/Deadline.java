public class Deadline extends Task {
    private String by;
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getType() {
        return "deadline";
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
