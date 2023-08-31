package TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(Boolean isDone, String name, LocalDateTime deadline) {
        super(isDone, name);
        this.deadline = deadline;
    }

    public String changeDeadlineFormat(LocalDateTime deadline) {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toText() {
        return "Deadline - "  + isDone + " - " + name + " - " + deadline + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + changeDeadlineFormat(deadline) + ")";
    }
}
