package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventTask(String description, String startTimeInput, String endTimeInput) {
        super(description);
        String[] possibleFormats = {
                "yyyy-MM-dd HH:mm",
                "yyyy/MM/dd HH:mm",
                "dd/MM/yyyy HH:mm",
                "MMM dd yyyy HH:mm",
        };
        for (String format : possibleFormats) {
            try {
                this.startTime = LocalDateTime.parse(startTimeInput, DateTimeFormatter.ofPattern(format));
                this.endTime = LocalDateTime.parse(endTimeInput, DateTimeFormatter.ofPattern(format));
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        if (this.startTime == null || this.endTime == null) {
            throw new DateTimeParseException("Invalid date format", startTimeInput, 0);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

}
