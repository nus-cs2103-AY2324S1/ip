import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
        this.byDateTime = byDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                byDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    @Override
    public String toSaveLine() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0,
                description, byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
