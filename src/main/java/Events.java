import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Events extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Events (String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String save() {
        return "E|" + super.status() + "|" + start + "|" + end;
    }
}
