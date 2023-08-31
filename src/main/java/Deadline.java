import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the time when the deadline is due.
     * 
     * @return Time when the deadline is due.
     */
    public LocalDateTime getCompleteBy() {
        return by;
    }

    /**
     * Returns a string representation of Deadline
     *
     * @return A string representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")) + ")";
    }
}
