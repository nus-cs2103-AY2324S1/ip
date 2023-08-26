import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate start;
    private LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyy")) + ")";
    }

    @Override
    public String toLine() {
        return " E | " + super.toLine() + " | "
                + this.start.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean fallsOn(LocalDate date) {
        return (date.isAfter(this.start) && date.isBefore(this.end))
                || date.isEqual(this.start) || date.isEqual(this.end);
    }
}
