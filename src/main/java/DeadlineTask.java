import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DeadlineTask extends Task{
    private LocalDateTime time;

    public DeadlineTask(String description, String timeInput) {
        super(description);
        this.time = LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
