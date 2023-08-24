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
}
