public class Deadline extends Task{
    private String type = "D";
    private String by;
    public Deadline(String title, String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (by: " + this.by + ")";
    }
}
