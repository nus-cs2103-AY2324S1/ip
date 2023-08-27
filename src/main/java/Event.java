import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime start;
    private LocalDateTime end;
    public Event(String task, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(task, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveFormat() {
        return "E|" + super.toSaveFormat()
                + String.format("|%s|%s\n", super.getSaveDateTime(start), super.getSaveDateTime(end));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format("(from: %s to: %s)", super.getDisplayDateTime(start), super.getDisplayDateTime(end));
    }
}
