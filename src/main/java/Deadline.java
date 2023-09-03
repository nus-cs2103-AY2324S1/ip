import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + 
                by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
