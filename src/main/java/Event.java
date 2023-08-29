import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String taskName, LocalDate start, LocalDate end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDescription() {
        return super.toString() + " | " + this.start + " | " + this.end;
    }

    public String getStartFormatted() {
        return this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getEndFormatted() {
        return this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String statusAndTask() {
        return "[E]" + statusString() + " " + super.toString() + " (from: " + getStartFormatted() + " to: " + getEndFormatted() + ")";
    }
}
