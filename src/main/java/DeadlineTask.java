import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {
    private LocalDateTime deadlineDate;
    public DeadlineTask(String description, String deadlineDate) {
        super(description);
        try {
            // time should be in format dd/mm/yyyy HHMM(24h)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            this.deadlineDate = LocalDateTime.parse(deadlineDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("There was an error parsing the date given.");
            e.printStackTrace();
        }
    }

    public String getType() {
        return "Deadline";
    }

    public String getDateTime() {
        return this.deadlineDate.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }
}