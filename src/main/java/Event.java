import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.validateEventDuration(from, to);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.isDone = isDone;
        this.validateEventDuration(from, to);
        this.from = from;
        this.to = to;
    }

    private void validateEventDuration(LocalDateTime from, LocalDateTime to) {
        if (from.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Start and end of event must be in the future.");
        if (from.isAfter(to)) throw new IllegalArgumentException("End of event must be after start of event.");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", super.generateDateString(from), super.generateDateString(to));
    }
}
