package simon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import simon.SimonException;


public class Deadline extends Task {
    public final LocalDateTime endDateTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String taskName, String endDate) throws SimonException {
        super(taskName);
        try {
            // If no time specified, append " 0000"
            if (!endDate.contains(" ")) {
                endDate += " 0000";
            }

            this.endDateTime = LocalDateTime.parse(endDate, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new SimonException("The date and time format is incorrect. Expected format: d/M/yyyy [HHmm].");
        }
    }

    @Override
    public String toString() {
        return " [D]" + (super.isDone ? "[X] " : "[ ] ") + super.toString() + " (by: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}
