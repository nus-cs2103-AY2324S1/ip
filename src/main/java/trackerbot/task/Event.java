package trackerbot.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

import trackerbot.exception.TrackerBotException;
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
     * Constructs the Event object from the given String input.
     *
     * @param desc The description of the Event task.
     * @param from The String representation of the start date to parse into a LocalDateTime object.
     * @param to The String representation of the end date to parse into a LocalDateTime object.
     * @throws DateTimeParseException if the deadline cannot be parsed by TaskDateHandler.
     * @see trackerbot.utils.TaskDateHandler#convertInputToDate(String, String)
     */
    public Event(String desc, String from, String to) throws TrackerBotException {
        super(desc);
        LocalDateTime[] fromToPair = TaskDateHandler.convertInputToDate(from, to);
        assert fromToPair.length == 2 : "input conversion to date failed without exception";
        this.from = fromToPair[0];
        this.to = fromToPair[1];

        assert this.to.isAfter(this.from) : "start date should be earlier than end date";
    }

    /**
     * Constructs the Event object using a String array, for use in save parsing.
     *
     * @param args The arguments for constructing Event, containing isDone status in index
     *             0, description in index 1 and an epoch Date string in index 2 and 3.
     * @throws TrackerBotException if the event dates cannot be parsed by TaskDateHandler,
     *                             or if the epoch save string is corrupted.
     * @see trackerbot.utils.TaskDateHandler#convertSaveToDate(String, String)
     */
    protected Event(String[] args) throws TrackerBotException {
        super(args);
        LocalDateTime[] fromToPair = TaskDateHandler.convertSaveToDate(args[2], args[3]);
        assert fromToPair.length == 2 : "input conversion to date failed without exception";
        from = fromToPair[0];
        to = fromToPair[1];

        assert to.isAfter(from) : "start date should be earlier than end date";
    }

    @Override
    public String toSaveString() {
        return "E|" + getSaveInfo() + "|"
                + from.toEpochSecond(ZoneOffset.UTC) + "|"
                + to.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * Returns the String representation of the Event object.
     * <p>This method appends the [E] tag in front of the Task toString, and
     * the event period to the end of the toString.</p>
     *
     * @return "[D]" prefixed to task.toString(), and "(from: [start] / to: [end])" postfixed to task.toString().
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + TaskDateHandler.convertDateToUi(from)
                + " | to: " + TaskDateHandler.convertDateToUi(to) + ")";
    }
}
