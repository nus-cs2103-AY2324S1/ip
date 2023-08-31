import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, String startTimeStr, String endTimeStr) throws DateTimeParseException {
        this(
                description,
                LocalDateTime.parse(startTimeStr, Task.INPUT_DATE_TIME_FORMATTER),
                LocalDateTime.parse(endTimeStr, Task.INPUT_DATE_TIME_FORMATTER));
    }

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isOngoing(LocalDateTime dateTime) {
        return !this.startTime.truncatedTo(ChronoUnit.DAYS).isAfter(dateTime.truncatedTo(ChronoUnit.DAYS))
                && !dateTime.truncatedTo(ChronoUnit.DAYS).isAfter(this.endTime.truncatedTo(ChronoUnit.DAYS));
    }

    @Override
    public String toCommaString() {
        String[] commaStringValues = {
                "E",
                this.isDone ? "X" : " ",
                this.description,
                this.startTime.format(Task.INPUT_DATE_TIME_FORMATTER),
                this.endTime.format(Task.INPUT_DATE_TIME_FORMATTER),
        };
        String commaString = String.join(",", commaStringValues);
        return commaString;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString()
                + " (from: " + this.startTime.format(Task.DISPLAY_DATE_TIME_FORMATTER)
                + " to: " + this.endTime.format(Task.DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
