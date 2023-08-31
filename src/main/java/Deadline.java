import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String deadlineDetails;
    private LocalDateTime time;

    public Deadline(String task, String deadlineDetails) {
        super(task);
        this.deadlineDetails = deadlineDetails;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dueDateTime = LocalDateTime.parse(deadlineDetails, formatter);
        this.time = dueDateTime;

    }


    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy");
        String timeToString = time.format(outputFormatter);
        return "[D]" + super.toString() + " (by: " + timeToString + ")";
    }
}
