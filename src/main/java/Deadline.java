import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String task, LocalDateTime by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedDate;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        formattedDate = by.format(outputFormatter);

        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}
