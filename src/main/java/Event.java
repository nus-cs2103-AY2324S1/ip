import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event task.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    String datePattern = "MMM-dd-yyyy HH:mm";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDescription() {
        String datePattern = "MMM-dd-yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return super.getDescription() + " (from: " + startTime.format(formatter) + " to: "
                + endTime.format(formatter) + ")";
    }

    public String getStartTime() {
        return startTime.format(formatter);
    }

    public String getEndTime() {
        return endTime.format(formatter);
    }

    public String getDescriptionWithoutTime() {
        return super.getDescription();
    }
}