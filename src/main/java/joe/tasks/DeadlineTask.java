package joe.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private static final String DATETIME_FORMAT = "dd MMM yyyy HH:mm";

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineString = this.deadline.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
        return String.format("[D]%s (by: %s)", super.toString(), deadlineString);
    }
}
