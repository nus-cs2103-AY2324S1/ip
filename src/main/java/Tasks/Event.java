package Tasks;

import Exceptions.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event that will happen. An <code>Event</code> object
 * has a <code>LocalDateTime</code> start and end time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public Event(String description) throws DukeException {
        super(description);
        // TODO: Check if /from is before /to
        // TODO: Date.now() when "today" is entered
        String[] fromParts = description.split("/from");
        String[] toParts = description.split("/to");

        if (fromParts.length >= 2) {
            this.description = fromParts[0].split("/to")[0].trim();
            String[] aftFrom = fromParts[1].split("/to");
            String startTimeString = aftFrom[0].trim(); // it should be in (d/M/yyyy h:m a) eg (15/3/2023 6:40 PM)

            // Format the string and parse it into startTime
            this.startTime = super.parseDateTime(startTimeString);
            if (aftFrom.length >= 2) {
                // '/to' is after '/from'
                String endTimeString = aftFrom[1].trim();
                // Format the string and parse it into endTime
                this.endTime = super.parseDateTime(endTimeString);
            } else {
                // '/to' is before '/from'
                String endTimeString = fromParts[0].split("/to")[1].trim();
                // Format the string and parse it into endTime
                this.endTime = super.parseDateTime(endTimeString);
            }
        } else if (toParts.length >= 2) {
            String[] parts = toParts[1].split("/from");
            this.description = toParts[0].trim();
            String endTimeString = parts[0].trim();
            // Format the string and parse it into endTime
            this.endTime = super.parseDateTime(endTimeString);
            if (parts.length > 1) {
                String startTimeString = parts[1].trim();
                // Format the string and parse it into endTime
                this.startTime = super.parseDateTime(startTimeString);
            }
        }
    }

    public Event(String description, String startTimeString, String endTimeString) {
        super(description);
        this.startTime = LocalDateTime.parse(startTimeString);
        this.endTime = LocalDateTime.parse(endTimeString);
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        if (startTime == null && endTime == null) return super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:m a");
        return super.toString() + String.format(
                " (from %s to %s)",
                startTime.format(formatter),
                endTime.format(formatter)
                );
    }

    @Override
    public String toFileString() {
        return "E | "
                + super.toFileString()
                + (startTime == null && endTime == null
                        ? ""
                        : String.format(" | %s | %s", startTime, endTime));
    }
}
