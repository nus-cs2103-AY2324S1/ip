public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadlines(boolean status, String description, String by) {
        super(status, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String dataFormat() {
        return "deadline/" +
                super.isDone.toString() + "/" +
                super.description + "/" +
                this.by;
    }
}
