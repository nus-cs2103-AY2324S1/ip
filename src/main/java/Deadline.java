public class Deadline extends Task{
    protected  String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon() + " (by: " + by + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, this.by);
    }
}
