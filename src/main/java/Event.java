import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Event extends Task {

    protected String from;
    protected String to;

    protected LocalDate fromDate;
    protected LocalDate toDate;

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.fromDate = parseDate(from);
        this.toDate = parseDate(to);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromWhen = from;
        String toWhen = to;

        if (fromDate != null) {
            fromWhen = fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (fromDateTime != null) {
            fromWhen = fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }

        if (toDate != null) {
            toWhen = toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } else if (toDateTime != null) {
            toWhen = toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return "[E]" + super.toString() + "(from: " + fromWhen + " to: " + toWhen + ")";
    }
    @Override
    public String writeToFile() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + "| " + from + " to " + to;
    }

    protected LocalDate parseDate(String date) {
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDate.parse(date, formats.get(i));
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }

    protected LocalDateTime parseDateTime(String date) {
        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDateTime.parse(date, formats.get(i));
            } catch (DateTimeParseException e) {

            }
        }

        return null;
    }
}