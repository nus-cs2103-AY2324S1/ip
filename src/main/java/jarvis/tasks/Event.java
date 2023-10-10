package jarvis.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jarvis.ui.Ui;

/**
 * Represents the "Event" task in Jarvis app.
 */
public class Event extends Task {

    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Initializes a new instance of the Event task.
     *
     * @param title        The title or description of the event.
     * @param fromDateTime The starting date and time of the event.
     * @param toDateTime   The ending date and time of the event.
     * @param isCompleted  A boolean indicating whether the event task is completed or not.
     */
    public Event(String title, LocalDateTime fromDateTime, LocalDateTime toDateTime, boolean isCompleted) {
        super(title, isCompleted);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Overrides the toString method to provide a custom string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Ui.getDefaultDateTimeFormat());
        String formattedFromDateTime = fromDateTime.format(formatter);
        String formattedToDateTime = toDateTime.format(formatter);
        return "E | " + super.toString() + " | " + formattedFromDateTime + " | " + formattedToDateTime;
    }
}
