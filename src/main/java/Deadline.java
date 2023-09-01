import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    /**
     * Constructor for Deadline.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String formatFile() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " " + by;
    }
    /**
     * Overrides the toString() method in Task.
     * @return the string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}