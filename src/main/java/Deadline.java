import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a task which needs to be completed by a certain date. As a result, it keeps track
 * of when it must be completed by.
 */
public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String composeToFileString() {
        return String.format("D|%s|%s", super.composeToFileString(), this.by.toString());
    }
}
