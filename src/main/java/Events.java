import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    LocalDateTime from, to;
    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]"
                + super.toString() + " (from: " + from.format(DATE_TIME_FORMATTER) +
                " to: " + to.format(DATE_TIME_FORMATTER) + ")";
    }
}
