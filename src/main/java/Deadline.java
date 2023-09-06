import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    LocalDateTime by;
    Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getType() {
        return "deadline";
    }
    @Override
    public String saveTask() {
        String data = "D | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data += this.getDescription();
        data = data + " | " + this.by + "\n"; // ISO-8601 e.g. 2023-09-06T14:30
        return data;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH);
        String formattedDateTime = by.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }
}
