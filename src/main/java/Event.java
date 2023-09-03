import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String writeFile() {
        return "E | " + super.writeFile() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                LocalDate.parse(this.from).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " +
                LocalDate.parse(this.to).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
