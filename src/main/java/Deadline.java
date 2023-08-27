import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description
            + " | " + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma"); // Print in 12-hour time format
        String timeString = this.time.format(timeFormatter).toLowerCase();

        return "[D]" + super.toString()
            + " (by: " + this.time.format(dateFormatter) + " " + timeString + ")";
    }
}
