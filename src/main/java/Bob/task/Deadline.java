package Bob.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = DateFormatter.format(by, "MMM d yyyy");
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = DateFormatter.format(by, "MMM d yyyy");
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D|%s|%s", super.convertToFileFormat(), this.by);
    }
}
