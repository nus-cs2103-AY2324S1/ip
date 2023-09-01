package noac.task;

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
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))  + ")";
    }


    @Override
    public String printToFile() {
        return "E|" + super.printToFile() + "|" + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + "|" + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
