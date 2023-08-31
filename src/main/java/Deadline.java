import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime end;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm");
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Deadline(String description, LocalDateTime end) {
        super(description);
        this.end = end;
    }
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.end.format(outputFormatter) + ")";
    }
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "D|" + stat + "|" + this.description + "|" + this.end.format(inputFormatter);
    }
}
