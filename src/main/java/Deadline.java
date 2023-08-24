public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)",
                this.getStatusIcon(),
                super.toString(),
                this.by);
    }
}
