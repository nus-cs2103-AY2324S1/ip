package simon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import simon.SimonException;


/**
 * The {@code Deadline} class extends the {@code Task} class and represents a task
 * that has a specific end date and time by which it should be completed.
 */
public class Deadline extends Task {
    /** The end date and time for this deadline task. */
    public final LocalDateTime endDateTime;

    /** Formatter to parse date and time input provided by the user. */
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy[ HHmm]");

    /** Formatter to format date and time for output display. */
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructs a new Deadline task.
     *
     * @param taskName The name or description of the task.
     * @param endDate The end date and time string for this deadline.
     * @throws SimonException If the provided date and time format is incorrect.
     */
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

    /**
     * Converts this Deadline task to a string format suitable for display.
     *
     * @return A string representation of this Deadline task.
     */
    @Override
    public String toString() {
        return " [D]" + (super.isDone ? "[X] " : "[ ] ") + super.toString() + " (by: " + endDateTime.format(OUTPUT_FORMATTER) + ")";
    }
}
