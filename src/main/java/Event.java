import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalTime to;
    public Event(String description, LocalDateTime from, LocalTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon() + "(from: " + from + " to: " + to + ")" ;
    }

    @Override
    public String toString() {
        return String.format("E | %d | %s | %s | %s", super.isDone ? 1 : 0, super.description, this.from, this.to);
    }
}
