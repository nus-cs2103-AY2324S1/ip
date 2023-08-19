public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String msg = "[D]" + super.toString() + " (by: " + this.by + ")";
        return msg;
    }
}
