package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDateTime;
    private LocalDate endDateTime;

    public Event(String description, String startDateTimeStr,String endDateTimeStr) {
        super(description);
        this.startDateTime = LocalDate.parse(startDateTimeStr);
        this.endDateTime = LocalDate.parse(endDateTimeStr);

    }

    public LocalDate getStartDateTime() {
        return startDateTime;
    }
    public LocalDate getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toFileString() {
        String type = "E";
        return type + " | " + (getIsDone() ? "1" : "0") + " | " +
                getDescription() + " | " + startDateTime + " to " + endDateTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to %s)", super.toString(), startDateTime, endDateTime );
    }


}
