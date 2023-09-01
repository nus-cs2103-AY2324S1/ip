import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

    public Event(String description,String startTime, String endTime) throws BenBenException {
        super(description);
        try {
            this.startTime = LocalDateTime.parse(startTime, formatter);
            this.endTime = LocalDateTime.parse(endTime, formatter);
            if (this.startTime.isAfter(this.endTime)) {
                throw new BenBenException("The end time should be later than the start time!");
            }
        } catch (DateTimeParseException e) {
            throw new BenBenException("The date and time is of the wrong format! Please use yyyy-MM-dd HH:mm");
        }
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
    }

    public String getStartTime() {
        return startTime.getMonth().toString()
                + " " + startTime.getDayOfMonth()
                + " " + startTime.getYear()
                + " " + startTime.getHour() + ":" + startTime.getMinute();
    }

    public String getEndTime() {
        return endTime.getMonth().toString()
                + " " + endTime.getDayOfMonth()
                + " " + endTime.getYear()
                + " " + endTime.getHour() + ":" + endTime.getMinute();
    }

    public String getFormattedStart() {
        return startTime.format(formatter);
    }

    public String getFormattedEnd() {
        return endTime.format(formatter);
    }

    @Override
    public String getLog() {
        return "E | " + (isDone? "1" : "0")
                + " | " + this.description
                + " | " + getFormattedStart()
                + " | " + getFormattedEnd() + System.lineSeparator();
    }
}