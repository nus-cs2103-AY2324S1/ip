package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task
 */
public class EventTask extends Task {
    /** The start time of the event */
    private LocalDateTime startTime;

    /** The end time of the event */
    private LocalDateTime endTime;

    /**
     * Constructor for EventTask
     *
     * @param description The description of the task
     * @param startTimeInput The start time of the task
     * @param endTimeInput The end time of the task
     */
    public EventTask(String description, String startTimeInput, String endTimeInput) {
        super(description);
        assert startTimeInput != null : "startTimeInput should not be null";
        assert endTimeInput != null : "endTimeInput should not be null";
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

    /**
    * Returns the start time and end time of the task
    *
    * @return The string representation of the start time and end time of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

}
