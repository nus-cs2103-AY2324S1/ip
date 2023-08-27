import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by, boolean marked) {
        super(description, "deadline");
        this.by = by;
        this.mark(marked, true);
    }

    @Override
    public String getOriginalMessage() {
        return "deadline " + this.getDescription() + " /by " + this.stringifyDate(this.by);
    }

    @Override

    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
