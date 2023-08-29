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

    String getStartDatetimeFormatted() {
        return this.startDatetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    String getEndDatetimeFormatted() {
        return this.endDatetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    String getStartDatetime() {
        return this.startDatetime.toString();
    }

    String getEndDatetime() {
        return this.endDatetime.toString();
    }

    @Override
    public String toString() {
        return "[E][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name + " (from: " + this.getStartDatetimeFormatted() + " to: " + this.getEndDatetimeFormatted() + ")";
    }

    String formatTaskForSaving() {
        return "[E][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name + " (from: " + this.getStartDatetime() + " to: " + this.getEndDatetime() + ")";
    }
}
