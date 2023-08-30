import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String data() {
        return "E " + super.data()
                + " /from " + DateTimeManager.dateToStringData(this.start)
                + " /to " + DateTimeManager.dateToStringData(this.end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeManager.dateToDisplay(this.start)
                + " to: " + DateTimeManager.dateToDisplay(this.end) + ")";
    }
}
