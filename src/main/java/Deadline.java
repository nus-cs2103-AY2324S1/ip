import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.validateDeadline(deadline);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description);
        this.isDone = isDone;
        this.validateDeadline(deadline);
        this.deadline = deadline;
    }

    private void validateDeadline(LocalDateTime deadline) {
        if (deadline.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Deadline must be in the future.");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", super.generateDateString(this.deadline));
    }
}
