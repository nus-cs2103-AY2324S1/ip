package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return " " + "[D]" + status + description + " (by: " + dateTime.format(formatter) + ")";
    }
}
