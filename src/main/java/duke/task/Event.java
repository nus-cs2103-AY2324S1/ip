package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task with a specified start and end dates and times.
 */
public class Event extends Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event task with a description, start date, end date and completion status.
     *
     * @param task The description of the task.
     * @param start The start date and time of the task.
     * @param end The end date and time of the task.
     * @param done The completion status of the task.
     */
    public Event(String task, String start, String end, boolean done) {
        super(task, done);
        this.start = stringToDateObj(start);
        this.end = stringToDateObj(end);
    }

    private LocalDateTime stringToDateObj(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * Returns a string representation of the Event task for Ui display.
     *
     * @return A formatted string including the task type, description, start date, and end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s, to: %s)", this.start.format(formatter), this.end.format(formatter));
    }

    /**
     * Returns a string representation of the Event task for saving to a file.
     *
     * @return A formatted string suitable for saving to a file.
     */
    @Override
    public String getTaskFileString() {
        return "E" + " , " + super.getTaskFileString() + " , " + this.start.format(formatter) + " , " + this.end.format(formatter);
    }
}
