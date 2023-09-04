package james;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    /**
     * The time of the deadline.
     */
    private LocalDateTime time;

    /**
     * Creates a new DeadlineTask with the given description and time.
     *
     * @param description The description of the task.
     * @param timeInput The time of the deadline.
     */

    public DeadlineTask(String description, String timeInput) {
        super(description);
        String[] possibleFormats = {
            "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd HH:mm",
            "dd/MM/yyyy HH:mm",
            "MMM dd yyyy HH:mm",
        };
        for (String format : possibleFormats) {
            try {
                this.time = LocalDateTime.parse(timeInput, DateTimeFormatter.ofPattern(format));
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        if (this.time == null) {
            throw new DateTimeParseException("Invalid date format", timeInput, 0);
        }
    }

    /**
     * Returns the string representation of the DeadlineTask.
     *
     * @return The string representation of the DeadlineTask.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
