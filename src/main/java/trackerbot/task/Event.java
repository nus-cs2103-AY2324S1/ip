package trackerbot.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import trackerbot.utils.TaskDateHandler;

/**
 * The Event class for TrackerBot, inheriting from the Task class. <br>
 * <p>This Task child contains 2 LocalDateTime objects to denote the interval to
 * complete the Task by.</p>
 * <p>As of version A-JavaDoc, the interval does not explicitly check for validity of
 * this time period.</p>
 *
 * @author WZWren
 * @version A-JavaDoc
 */
public class Event extends Task {
    /** The start date of the event. **/
    private LocalDateTime from;

    /** The end date of the event. **/
    private LocalDateTime to;

    /**
     * Constructor for the class.
     * @see trackerbot.utils.TaskDateHandler#convertInputToDate
     * @param desc The description of the Event task.
     * @param from The String representation of the start date to parse into a LocalDateTime object.
     * @param to The String representation of the end date to parse into a LocalDateTime object.
     * @throws DateTimeParseException if the deadline cannot be parsed by TaskDateHandler.
     */
    public Event(String desc, String from, String to) throws DateTimeParseException {
        super(desc);
        this.from = TaskDateHandler.convertInputToDate(from);
        this.to = TaskDateHandler.convertInputToDate(to);
    }

    /**
     * Constructs a Event using a String array, for use in save parsing.
     * @see trackerbot.utils.TaskDateHandler#convertSaveToDate
     * @param args The arguments for constructing Event, containing isDone status in index
     *             0, description in index 1 and an epoch Date string in index 2 and 3.
     * @throws DateTimeParseException if the event dates cannot be parsed by TaskDateHandler.
     * @throws NumberFormatException if the epoch save string is corrupted.
     */
    protected Event(String[] args) throws DateTimeParseException, NumberFormatException {
        super(args);
        this.from = TaskDateHandler.convertSaveToDate(args[2]);
        this.to = TaskDateHandler.convertSaveToDate(args[3]);
    }

    @Override
    public String toSaveString() {
        return "E|" + getSaveInfo() + "|"
                + this.from.toEpochSecond(ZoneOffset.UTC) + "|"
                + this.to.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * toString method of Event. <br>
     * The String representation of To-do appends the [E] tag in front of the Task toString, and
     * the event period to the end of the toString.
     * @return "[D]" prefixed to task.toString(), and "(from: [start] / to: [end])" postfixed to task.toString().
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + TaskDateHandler.convertDateToUi(this.from)
                + " | to: " + TaskDateHandler.convertDateToUi(this.to) + ")";
    }
}
