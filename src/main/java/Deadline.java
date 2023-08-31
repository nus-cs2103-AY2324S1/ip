import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime time;

    public Deadline(String task, String deadlineDetails) {
        super(task);
        LocalDateTime dueDateTime = Parser.formatDate(deadlineDetails);
        this.time = dueDateTime;
    }


    @Override
    public String toString() {
        String timeToString = Parser.dateToString(this.time);
        return "[D]" + super.toString() + " (by: " + timeToString + ")";
    }
}
