import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;


    Event(String message, LocalDateTime from, LocalDateTime to) {
        super(message);
        this.from = from;
        this.to = to;
    }

    public String toSaveFormatString() {
        return "E | " + this.getStatusNumber() + " | " + this.message + " | " + from + " | " + to;
    }

    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.message
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:MM:SS"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:MM:SS")) + ")";
    }
}
