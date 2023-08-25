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
        return "[E]" + super.toString() + " (from: " + formatLocalDateTime(from) + " to: " + formatLocalDateTime(to) + ")";
    }


    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, serializeLocalDateTime(from), serializeLocalDateTime(to));
    }
}