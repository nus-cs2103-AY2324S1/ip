import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
    protected LocalDate from;
    protected LocalDate to;
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String description, boolean isDone, String from, String to) throws DukeException {
        super(description, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        String fromString = from.format(DATE_TIME_FORMATTER);
        String toString = to.format(DATE_TIME_FORMATTER);
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
