public class Deadline extends Task {
    protected String by;
    private static final long serialVersionUID = 6548478093468504893L;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String taskString() {
        return "[D]" + super.taskString() + "(by: " + by + ")";
    }
}