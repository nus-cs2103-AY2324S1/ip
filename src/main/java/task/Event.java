package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an extension of a task, containing a start and end time in terms of LocalDate.
 */
public class Event extends Task {
    private final LocalDate timeStart;
    private final LocalDate timeEnd;

    /**
     * Constructs an event.
     * @param name name of the Event.
     * @param timeStart starting time of the Event.
     * @param timeEnd ending time of the Event.
     */
    public Event(String name, LocalDate timeStart, LocalDate timeEnd) {
        super(name);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Constructs an Event, with a boolean representing if it is marked.
     * @param name name of the Event.
     * @param isMarked whether the event is marked.
     * @param timeStart starting time of the Event.
     * @param timeEnd ending time of the Event.
     */
    public Event(String name, boolean isMarked, LocalDate timeStart, LocalDate timeEnd) {
        super(name, isMarked);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    /**
     * Creates a readable string interpretation of the Event.
     * @return a readable Event in String form.
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + timeStart.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + timeEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")");
    }

    /**
     * Produces a saveable format of the Event.
     * (We use dividers to minimize the confusion caused by spaces in names.)
     * @return a String format of Event with minimal ambiguity in format.
     */
    @Override
    public String fileFormat() {
        return "EV" + DIVIDER + super.fileFormat() + DIVIDER + timeStart + DIVIDER + timeEnd;
    }
}

