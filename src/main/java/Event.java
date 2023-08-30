import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static String invalidDate = "Please provide dates with the following format: YYYY-MM-DD";

    Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    boolean isToday(String dateStr) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return this.from.compareTo(date) <= 0 && date.compareTo(to) <= 0;
        } catch (DateTimeException e) {
            throw new DukeException(invalidDate);
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s - %s)", super.toString(), 
            this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")), 
            this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String exportToText() {
        return String.format("event,>%s,>%s,>%s", super.exportToText(), this.from, this.to);
    }
}
