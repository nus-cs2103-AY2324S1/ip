package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a specific task of type Event.
 *
 * Contains a start time, and an end time.
 */
public class EventTask extends TimedTask {
    /**
     * Constructor for an Event Task
     *
     * @param itemName The name of the task
     * @param fromDateTime The time the event starts
     * @param toDateTime The time the event ends
     */
    public EventTask(String itemName, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(itemName, fromDateTime, toDateTime);

    }

    /**
     * Constructor for an Event Task
     *
     * @param id The saved task ID
     * @param itemName The name of the task
     * @param fromDateTime The time the event starts
     * @param toDateTime The time the event ends
     */
    public EventTask(int id, String itemName, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(id, itemName, fromDateTime, toDateTime);

    }


    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String encodeTask() {
        return super.encodeTask() + " | " + this.getStart() + " | " + this.getEnd();
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: " + this.getStart().format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"))
                + " to: " + this.getEnd().format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public int compareTo(TimedTask o) {
        return this.getStart().compareTo(o.getStart());
    }
}
