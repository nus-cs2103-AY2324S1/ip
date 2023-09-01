package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a specific task of type Deadline.
 *
 * Stores the deadline as a LocalDateTime.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadlineDateTime;

    /**
     * Constructor for a Deadline Task.
     *
     * @param itemName The name of the task
     * @param deadlineDateTime The deadline
     */
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
