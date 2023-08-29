import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " | " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
