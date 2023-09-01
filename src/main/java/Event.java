import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends UserInput {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toSaveFormat() {
        return "E" + super.toSaveFormat() + " | " + this.from + "-" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) +")";
    }
}
