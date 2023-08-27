public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    @Override
    public String getString() {
        return "[D]" + super.getString() + " (by: " + this.by + ")";
    }

    @Override
    public String getFileString() {
        return "D|" + super.getFileString() + "|" + this.by;
    }
}
