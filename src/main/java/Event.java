import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    public Event(String description, LocalDate start,LocalDate end) throws DukeEndDateBeforeStartDateException {
        super(description);
        if (end.isBefore(start)) {
            throw new DukeEndDateBeforeStartDateException();
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String msg = "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH))
                + " to: "
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")";
        return msg;
    }
}
