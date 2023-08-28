import java.time.LocalDateTime;

public class Deadline extends Task {
    // protected String by;
    protected LocalDateTime by;

    public Deadline (String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    public Deadline (String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.getDate(this.by) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by;
    }
}