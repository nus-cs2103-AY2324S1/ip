package duke.task;

import duke.parse.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A class to represent an event.
 * An event has a start time and an end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Instantiates an event with the given content, start time and end time.
     * @param name the content of the event
     * @param start the start time of the event
     * @param end the end time of the event
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Data representation of the event, to be stored in the disk.
     * @return data representation of the event to be stored in disk
     */
    @Override
    public String data() {
        return "E " + super.data()
                + " /from " + DateTimeManager.dateToStringData(this.start)
                + " /to " + DateTimeManager.dateToStringData(this.end);
    }

    /**
     * Checks whether this event is happening on the given date.
     * @param date the datetime to check against
     * @return whether the event is happening on the given date
     */
    @Override
    public boolean containsDate(LocalDate date) {
        return (this.start.toLocalDate().isBefore(date) || this.start.toLocalDate().equals(date))
                && (this.end.toLocalDate().isAfter(date) || this.end.toLocalDate().equals(date));
    }

    /**
     * String representation of this event, to be printed in UI.
     * @return string representation of this event to be printed in UI
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeManager.dateToDisplay(this.start)
                + " to: " + DateTimeManager.dateToDisplay(this.end) + ")";
    }

    /**
     * Checks whether this event is the same as another task.
     * It is the same if it is the same event, with same content, start and end time.
     * @param another the object to compare with
     * @return whether this event is the same as another task
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Event) {
            Event anotherEvent = (Event) another;
            return super.equals(another)
                    && this.start.equals(anotherEvent.start)
                    && this.end.equals(anotherEvent.end);
        }
        return false;
    }
}
