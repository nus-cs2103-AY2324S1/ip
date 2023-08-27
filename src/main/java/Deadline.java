import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    final DateTimeFormatter PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    private LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getFormattedBy(DateTimeFormatter formatter) {
        return by.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), getFormattedBy(PRINT_FORMATTER));
    }

    @Override
    public String getStorageString() {
        return "D | " + super.getStorageString() + " | " + getFormattedBy(PARSE_FORMATTER);
    }
}
