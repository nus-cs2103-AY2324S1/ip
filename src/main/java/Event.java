import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalTime;
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String startDate;
        String endDate;

        DateTimeFormatter outputFormatterFrom = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        startDate = from.format(outputFormatterFrom);

        DateTimeFormatter outputFormatterTo = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        endDate = to.format(outputFormatterTo);

        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
