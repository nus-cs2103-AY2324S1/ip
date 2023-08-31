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
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + this.from.format(formatter) + " to: " + this.to.format(formatter) + ")";
    }

    @Override
    public String encode() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return "E"+getStatusNumber()+super.description + " /from " + this.from.format(formatter) + " /to " + this.to.format(formatter);
    }
}