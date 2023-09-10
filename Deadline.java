import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description, dateTime);
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date;
    }
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return " " + "[D]" + status + description + " (by: " +
                dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
