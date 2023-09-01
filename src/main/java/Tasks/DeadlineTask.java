package Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private String deadline = "";
    private LocalDate deadlineDate;
    private int deadlineTime;
    public DeadlineTask(String itemName, String deadline) {
        super(itemName);
        this.deadline = deadline;
    }

    public DeadlineTask(String itemName, LocalDate deadlineDate, int deadlineTime) {
        super(itemName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String encodeTask() {
        return super.encodeTask() + " | " + this.deadlineDate + " " + String.valueOf(deadlineTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + LocalTime.parse(String.valueOf(deadlineTime), DateTimeFormatter.ofPattern("HHmm")).format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}
