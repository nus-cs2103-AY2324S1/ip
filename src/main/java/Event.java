import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate dateFrom;
    protected LocalDate dateTo;
    public Event(String description, String dateFrom, String dateTo) throws DateTimeParseException {
        super(description);
        this.dateFrom = LocalDate.parse(dateFrom);
        this.dateTo = LocalDate.parse(dateTo);
    }

    @Override
    public String toLogString() {
        return String.format("E|%s|%s|%s|%s", (isDone ? "X" : "O"), description, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        String taskString = String.format(" (from: %s to: %s)",
                dateFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                dateTo.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[E]" + super.toString() + taskString;
    }
}
