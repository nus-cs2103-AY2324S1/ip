package trackerbot.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import trackerbot.exception.TrackerBotException;
import trackerbot.utils.TaskDateHandler;

/**
 * The Deadline class for TrackerBot, inheriting from the Task class. <br>
 * This Task child contains a LocalDateTime object to complete the task by.
 *
 * @author WZWren
 * @version A-JavaDoc
 */
public class Deadline extends Task {
    /** The Deadline of the task. **/
    private LocalDateTime by;

    /**
     * Constructs a Deadline object from the given string input.
     *
     * @param desc The description of the Deadline task.
     * @param by The String representation of the deadline to parse into a LocalDateTime object.
     * @throws TrackerBotException if the deadline cannot be parsed by TaskDateHandler.
     * @see trackerbot.utils.TaskDateHandler#convertInputToDate
     */
    public Deadline(String desc, String by) throws TrackerBotException {
        super(desc);
        this.by = TaskDateHandler.convertInputToDate(by);
    }

    /**
     * Constructs a Deadline using a String array, for use in save parsing.
     *
     * @param args The arguments for constructing Deadline, containing isDone status in index
     *             0, description in index 1 and an epoch Date string in index 2
     * @throws TrackerBotException if the deadline cannot be parsed by TaskDateHandler.
     *                             or if the epoch save string is corrupted.
     * @see trackerbot.utils.TaskDateHandler#convertSaveToDate
     */
    protected Deadline(String[] args) throws TrackerBotException {
        super(args);
        by = TaskDateHandler.convertSaveToDate(args[2]);
    }

    @Override
    public String toSaveString() {
        return "D|" + getSaveInfo() + "|" + by.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * Returns the String representation of the Deadline.
     * <p>This method appends the [D] tag in front of the Task toString, and
     * the deadline date to the end of the toString.</p>
     *
     * @return "[D]" prefixed to task.toString(), and "(by: [deadline])" postfixed to task.toString().
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + TaskDateHandler.convertDateToUi(by) + ")";
    }
}
