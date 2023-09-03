import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    final String taskChar = "[D]";
    protected LocalDate dueDate;
    protected LocalTime dueTime;

    public Deadlines(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.dueDate = deadlineDate;
        this.dueTime = deadlineTime;
    }

    public String formatDate() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String formatTime() {
        return this.dueTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toFileString() {
        String taskDetails = super.getTaskName() + " | " + formatDate() + " " + formatTime();
        return "D | " + super.getStatusNumber() + " | " + taskDetails;
    }

    @Override
    public String toString() {
        return taskChar  + super.toString() + " (by: " + formatDate() + " " + formatTime() + ")";
    }
}
