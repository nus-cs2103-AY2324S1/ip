public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public String byString() {
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}

