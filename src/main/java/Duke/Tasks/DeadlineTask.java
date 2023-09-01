package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime deadlineDateTime;

    public DeadlineTask(String itemName, LocalDateTime deadlineDateTime) {
        super(itemName);
        this.deadlineDateTime = deadlineDateTime;

    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String encodeTask() {
        return super.encodeTask() + " | " + deadlineDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
