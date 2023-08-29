import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class Events extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("dd MM yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("dd MM yyyy HHmm")) + ")";
    }
}
