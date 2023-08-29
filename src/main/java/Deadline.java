import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime deadlineDate;

    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description, "D");
        this.deadlineDate = deadlineDate;
    }

    public String toString() {
        return super.toString() + " (by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + deadlineDate;
    }
}
