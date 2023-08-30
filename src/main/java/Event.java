import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String outputFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String outputTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[E]" + super.toString() + " (from: " + outputFrom + " to: " + outputTo + ")";
    }

    @Override
    public String write() {
        return "E | " + super.write() + " | " + from.toString() + " | " + to.toString();
    }
}