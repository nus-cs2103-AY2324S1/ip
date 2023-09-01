package trackerbot.task;

import trackerbot.utils.TaskDateHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

/**
 * The Event class for TrackerBot, inheriting from the Task class. <br>
 * This Event child contains the start and end times to complete the task by.
 *
 * @author WZWren
 * @version Level-4
 */
public class Event extends Task {
    /** The start date of the event. **/
    LocalDateTime from;

    /** The end date of the event. **/
    LocalDateTime to;

    /**
     * The constructor for the class.
     * @param desc The description of the Deadline task.
     */
    public Event(String desc, String from, String to) throws DateTimeParseException {
        super(desc);
        this.from = TaskDateHandler.convertInputToDate(from);
        this.to = TaskDateHandler.convertInputToDate(to);
    }

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
