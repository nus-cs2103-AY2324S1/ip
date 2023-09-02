import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Date given is in an invalid format.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")) + ")";
    }

    @Override
    public String getDescription() {
        return "D" + super.getDescription() + " | " + this.by.format(formatter);
    }
}
