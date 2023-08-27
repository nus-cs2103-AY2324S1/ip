public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by, boolean marked) {
        super(description, "deadline");
        this.by = by;
        this.mark(marked, true);
    }

    @Override
    public String getOriginalMessage() {
        return "deadline " + this.getDescription() + " /by " + this.by;
    }

    @Override

    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
