import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDate by;
    protected LocalTime time;

    public Deadline(String description, LocalDate by, LocalTime time) {
        super(description);
        this.by = by;
        this.time = time;
    }

    @Override
    public String toString() {
        if (time == null) {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + time.format(DateTimeFormatter.ofPattern(" hh:mm a"))+ ")";
        }

    }

    public String save() {
        if (time != null) {
            return "D|" + super.status() + "|" + by + time.format(DateTimeFormatter.ofPattern(" hhmm"));
        } else {
            return "D|" + super.status() + "|" + by;
        }
    }
}
