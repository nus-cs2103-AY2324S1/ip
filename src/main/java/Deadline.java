public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return super.toString() + " | " + by ;
    }

    @Override
    public String statusAndTask() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

}
