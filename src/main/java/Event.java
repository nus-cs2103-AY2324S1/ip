import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
            + " | " + this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma"); // Print in 12-hour time format
        String timeString = this.time.format(timeFormatter).toLowerCase();

        return "[E]" + super.toString()
            + " (at: " + this.time.format(dateFormatter) + " " + timeString + ")";
    }
}
