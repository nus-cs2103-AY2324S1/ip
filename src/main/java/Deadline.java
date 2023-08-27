import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    // Constants
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Fields
    LocalDate deadline;

    // Constructor for Deadline
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), deadline.format(FORMAT));
    }
}
