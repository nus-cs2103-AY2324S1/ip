import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    LocalDate startDatetime;
    LocalDate endDatetime;

    Event(String name, String startDatetime, String endDatetime) {
        super(name);
        this.startDatetime = LocalDate.parse(startDatetime);
        this.endDatetime = LocalDate.parse(endDatetime);
    }

    @Override
    String getTaskType() {
        return "Event";
    }

    String getStartDatetime() {
        return this.startDatetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    String getEndDatetime() {
        return this.endDatetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E][" + (this.isDone() ? 'X' : ' ') + "] " + this.name + " (from: " + this.getStartDatetime() + " to: " + getEndDatetime() + ")";
    }
}
