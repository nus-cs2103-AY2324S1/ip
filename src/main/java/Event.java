import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String start;
    private String end;
    private LocalDate localDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String task, LocalDate date, LocalTime start, LocalTime end) {
        super(task);
        this.localDate = date;
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toList() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("ha");
        return "E" + super.toList() + " | " + this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " " + this.startTime.format(timeFormat) + " to " + this.endTime.format(timeFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h a");
        return "[E]" + super.toString() + " (from: " +
                this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " " + this.startTime.format(timeFormat) + " to: " + this.endTime.format(timeFormat) + ")";

    }
}
