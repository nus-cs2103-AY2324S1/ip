import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.localDateTimeToString(from) + " to: " +
                super.localDateTimeToString(to) + ")";
    }

    public String toFile() {
        return "E" + super.toFile() +   this.from + "--" + this.to;
    }
}
