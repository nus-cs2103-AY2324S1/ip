package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * A Task which has a description, when it starts and when it ends.
 *
 * The start and end is of type LocalDateTime and is used to specify when the Task begins and finishes.
 * Has a tag E.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event with description, start and end.
     *
     * @param description The description of the task.
     * @param start The date and time when the event starts.
     * @param end The date and time when the event ends.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end){
        super(description);
        this.start = start;
        this.end = end;
        this.tag = Tag.E;
    }

    /**
     * Changes the format to be more readable.
     *
     * The initial format of LocalDateTime is YYYY-MM-DDTHH:MM:SS.
     * This is turned into Month Day Year, Time, which increases
     * its readability.
     *
     * @param date The date and time to format.
     * @return Newly formatted LocalDateTime.
     */
    public String changeFormat(LocalDateTime date){
        return date.getMonth().toString() + " " + date.getDayOfMonth() + " " +
                date.getYear() + ", " + date.toLocalTime();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString(){
        return String.format("%s (from: %s to: %s)", description, changeFormat(start),
                changeFormat(end));
    }

}
