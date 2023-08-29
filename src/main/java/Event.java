import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
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
        String formattedStart = this.start.format(DateTimeFormatter.ofPattern("yyyy-mm-dd HHmm"));
        String formattedEnd = this.end.format(DateTimeFormatter.ofPattern("yyyy-mm-dd HHmm"));
        return super.toString() + " | " + formattedStart + " | " + formattedEnd;
    }

    public String getStartFormatted() {
        return this.start.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
    }

    public String getEndFormatted() {
        return this.end.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
    }

    @Override
    public String statusAndTask() {
        return "[E]" + statusString() + " " + super.toString() + " (from: " + getStartFormatted() + ")(to: " + getEndFormatted() + ")";
    }
}
