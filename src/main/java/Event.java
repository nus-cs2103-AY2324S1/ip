import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private static final long serialVersionUID = -4674004457612195474L;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String taskString() {
        return "[E]" + super.taskString() + "(from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + "to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}