import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatLocalDate(from) + " to: " + formatLocalDate(to) + ")";
    }


    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, formatLocalDate(from), formatLocalDate(to));
    }
}