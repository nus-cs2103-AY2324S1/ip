public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, char taskType) {
        super(description, taskType);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + " | " + by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
