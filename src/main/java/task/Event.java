package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate timeStart;
    private LocalDate timeEnd;
    public Event(String name, LocalDate timeStart, LocalDate timeEnd) {
        super(name);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    public Event(String name, boolean completeStatus, LocalDate timeStart, LocalDate timeEnd) {
        super(name, completeStatus);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public String toString() {
        return ("[E]" + super.toString() + " (from: " + timeStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + timeEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }
    public String fileFormat() {
        return "EV" + DIVIDER + super.fileFormat() + DIVIDER + timeStart + DIVIDER + timeEnd;
    }
}
