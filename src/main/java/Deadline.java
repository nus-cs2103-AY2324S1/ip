import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(LocalDateTime dueDate, String description) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[D]" + super.toString() + " (by: " + this.dueDate.format(format) + ")";
    }

    @Override
    public String toStringWithDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[D]" + super.toString() + "DATETIME " + this.dueDate.format(format);
    }
}
