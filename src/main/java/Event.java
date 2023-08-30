import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
