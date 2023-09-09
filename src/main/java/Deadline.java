import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Deadline extends Task {
    // protected String by;
    private LocalDate deadline;
    public Deadline(String description, String deadlineString) {
        super(description);
        this.deadline = parseDeadline(deadlineString);
    }

    private LocalDate parseDeadline(String deadlineString) {
        try {
            if(!deadlineString.isEmpty()) {
                return LocalDate.parse(deadlineString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + deadlineString);
        }
        return null;
    }
    private String formatDeadline(LocalDate deadlineDate) {
        if (deadlineDate == null) {
            return "Invalid date.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return deadlineDate.format(formatter);
    }
    private String saveFormatDeadline(LocalDate deadlineDate) {
        if (deadlineDate == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return deadlineDate.format(formatter);
    }
    @Override
    public String toDataString() {
        return "DEADLINE | " + super.toDataString() + " | " + saveFormatDeadline(deadline);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline(deadline) + ")";
    }
}
