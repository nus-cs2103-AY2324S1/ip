import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(Task.DATE_TIME_FORMAT));
    }

    @Override
    public String encodeTask() {
        return String.format("D;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.by);
    }
}
