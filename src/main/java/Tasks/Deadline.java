package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate parsedBy; // New field to store parsed LocalDate

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        parseBy();
    }

    public Deadline(String description, String by, boolean mark) {
        super(description, mark);
        this.by = by;
        parseBy();
    }

    private void parseBy() {
        DateTimeFormatter[] dateFormats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MMM dd yyyy")
        };

        for (DateTimeFormatter dateFormat : dateFormats) {
            try {
                parsedBy = LocalDate.parse(by, dateFormat);
                break; // Successfully parsed, so no need to try other formats
            } catch (DateTimeParseException e) {

            }
        }
    }

    @Override
    public String toString() {
        String dateString = parsedBy != null ?
                parsedBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) :
                by;

        return "[D]" + super.toString() + " by: " + dateString;
    }
}
