import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }

    @Override
    public String toWriteString() {
        return "D | " + (isDone ? "X" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy ha"));
    }

}