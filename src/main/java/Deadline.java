public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description.strip());
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
