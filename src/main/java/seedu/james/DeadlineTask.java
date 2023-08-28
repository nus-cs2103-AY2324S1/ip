package seedu.james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class DeadlineTask extends Task {
    private LocalDateTime time;

    public DeadlineTask(String description, String timeInput) {
        super(description);
        String[] possibleFormats = {
                "yyyy-MM-dd HH:mm",
                "yyyy/MM/dd HH:mm",
                "dd/MM/yyyy HH:mm",
                "MMM dd yyyy HH:mm",
        };
        for (String format : possibleFormats) {
            try {
                this.time = LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern(format));
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        if (this.time == null) {
            throw new DateTimeParseException("Invalid date format", timeInput, 0);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
