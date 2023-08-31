import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.dateTimeFormatter(from) + " to: "
                + Parser.dateTimeFormatter(to) + ")";
    }

    @Override
    public String storageFormat() {
        return ("E" + super.storageFormat() + " | " + from + " | " + to);
    }
}
