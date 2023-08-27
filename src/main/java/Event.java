import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return "E";
    }

    public String getStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName()
                + " (from: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }
}
