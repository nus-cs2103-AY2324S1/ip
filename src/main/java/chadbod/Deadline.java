package chadbod;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the ChadBod application, extending the Task class.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     *
     * @param description the description of the deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                    this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss")));
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description,
                    this.by);
    }
}
