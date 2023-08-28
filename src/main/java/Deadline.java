public class Deadline extends Task {
    protected String type = "D";
    protected String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }

    @Override
    public String getTimeInfo() {
        return "(by: " + this.by + ")";
    }
}
