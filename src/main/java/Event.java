import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.fromDate = from;
        this.toDate = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.fromDate = from;
        this.toDate = to;
    }

    public boolean isHappeningOn(LocalDate date) {
        return !(this.toDate.isBefore(date) || this.fromDate.isAfter(date));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
