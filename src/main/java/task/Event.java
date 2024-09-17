package task;

import exception.DukeException;
import exception.InvalidTaskFormatException;

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
        initializeEvent();
    }

    private void initializeEvent() throws DukeException {
        String[] splitString = description.split("/from");

        if (splitString.length < 2) throw new InvalidTaskFormatException();

        assert !splitString[0].isBlank();
        assert !splitString[1].isBlank();
        this.description = splitString[0].split("/to")[0].trim();
        String[] partFromTo = splitString[1].split("/to");
        String startTimeString = partFromTo[0].trim();
        this.startTime = super.parseDateTime(startTimeString);
        String endTimeString;
        if (partFromTo.length >= 2) {
            // Formats the string if '/to' comes after '/from'
            endTimeString = partFromTo[1].trim();
        } else {
            // Formats the string if '/to' comes before '/from'
            endTimeString = splitString[0].split("/to")[1].trim();
        }
        this.endTime = super.parseDateTime(endTimeString);
    }

    public Event(String description, String startTimeString, String endTimeString) {
        super(description);
        assert !startTimeString.isBlank() && !endTimeString.isBlank() : "No start and end time";
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
