package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String start;
    protected String end;
    protected LocalDate parsedStartDate;
    protected LocalDate parsedEndDate;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        parseDates();
    }

    public Event(String description, String start, String end, boolean mark) {
        super(description, mark);
        this.start = start;
        this.end = end;
        parseDates();
    }

    private void parseDates() {
        DateTimeFormatter[] dateFormats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MMM dd yyyy")
        };

        for (DateTimeFormatter dateFormat : dateFormats) {
            try {
                parsedStartDate = LocalDate.parse(start, dateFormat);
                parsedEndDate = LocalDate.parse(end, dateFormat);
                break;
            } catch (DateTimeParseException e) {
                parsedStartDate = null;
                parsedEndDate = null;
            }
        }
    }

    @Override
    public String toString() {
        String startString = parsedStartDate != null ?
                parsedStartDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) :
                start;

        String endString = parsedEndDate != null ?
                parsedEndDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) :
                end;

        return "[E]" + super.toString() + " from " + startString + " to " + endString;
    }
}
