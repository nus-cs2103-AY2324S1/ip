import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return "D " + super.fileFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}