package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents an Event, which is a Task with a start and end date
 */
public class Event extends Task {
    public LocalDate begin;
    public LocalDate end;

    /**
     * Constructs a new Event
     * @param name Name of the event
     * @param begin Date of when the event begins
     * @param end Date of when the event ends
     * @param isDone Status of whether the event is done
     */
    public Event(String name, LocalDate begin, LocalDate end, String isDone) {
        super(name, isDone);
        this.begin = begin;
        this.end = end;
    }

    /**
     * Returns the string representation of the event to be saved
     * @return String containing the status of event, name of event, begin and end dates
     */
    @Override
    public String toDataString() {
        return super.toDataString() + " | " + begin.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                " | " + end.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * Returns the string representation of the event to be printed
     * @return String containing the status of event, name of event, begin and end dates
     */
    @Override
    public String toString() {
        String str = "[E] " + super.getStatus() + " " + super.name + " (from: " +
                begin.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return str;
    }
}
