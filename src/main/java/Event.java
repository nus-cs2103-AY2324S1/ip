import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String taskName, String startDate, String endDate) throws SimonException {
        super(taskName);
        try {
            this.startDateTime = LocalDateTime.parse(startDate, INPUT_FORMATTER);
            this.endDateTime = LocalDateTime.parse(endDate, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SimonException("The date and time format is incorrect. Expected format: d/M/yyyy HHmm.");
        }
    }

    @Override
    public String toString() {
        return " [E]" + (super.isDone ? "[X] " : "[ ] ") + super.toString() +
                " (from: " + startDateTime.format(OUTPUT_FORMATTER) +
                " to: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}