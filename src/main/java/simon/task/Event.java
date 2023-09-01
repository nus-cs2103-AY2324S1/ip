package simon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import simon.SimonException;



public class Event extends Task {

    public LocalDateTime startDateTime;
    public LocalDateTime endDateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Event(String taskName, String startDate, String endDate) throws SimonException {
        super(taskName);
        try {
            // If no time specified, append " 0000"
            if (!startDate.contains(" ")) {
                startDate += " 0000";
            }
            if (!endDate.contains(" ")) {
                endDate += " 0000";
            }

            this.startDateTime = LocalDateTime.parse(startDate, INPUT_FORMATTER);
            this.endDateTime = LocalDateTime.parse(endDate, INPUT_FORMATTER);

            // Validate that endDateTime is after startDateTime
            if (!endDateTime.isAfter(startDateTime)) {
                throw new SimonException("The end time should be after the start time.");
            }

        } catch (DateTimeParseException e) {
            throw new SimonException("The date and time format is incorrect. Expected format: d/M/yyyy [HHmm].");
        }
    }

    @Override
    public String toString() {
        return " [E]" + (super.isDone ? "[X] " : "[ ] ") + super.toString() +
                " (from: " + startDateTime.format(OUTPUT_FORMATTER) +
                " to: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}
