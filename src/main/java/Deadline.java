public class Deadline extends Task{
    private String type = "D";
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + this.by + ")";
    }
}
