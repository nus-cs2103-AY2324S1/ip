import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + this.by;
    }

    public String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(this.by) + ")";
    }
}
