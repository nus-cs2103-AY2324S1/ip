import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    private String start;
    private LocalDate startDate;
    private String end;
    private LocalDate endDate;
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        this.type = "E";
    }
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "E";
    }
    @Override
    public String toString() {
        return super.toString() + "(from: " + (start.isEmpty() ? startDate : start)
                + " to: " + (end.isEmpty() ? endDate : end) + ")";
    }
}
