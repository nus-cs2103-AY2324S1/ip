import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileFormat() {
        return String.format("E%s%s%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.from.format(Time.FORMATTER),
                Storage.SEPARATOR, this.to.format(Time.FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(Time.DISPLAY_FORMATTER),
                this.to.format(Time.DISPLAY_FORMATTER));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return super.equals(event) && this.from.equals(event.from) && this.to.equals(event.to);
        }
        return false;
    }
}
