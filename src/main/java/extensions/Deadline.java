package extensions;
/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    String day;
    public Deadline(String description, String day) {
        super(description);
        this.day = day;
    }
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)",
                this.getDoneStatus(),
                this.description,
                this.day);
    }
}