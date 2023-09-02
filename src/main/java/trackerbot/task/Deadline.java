package trackerbot.task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

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
     * Constructor for the class.
     * @see trackerbot.utils.TaskDateHandler#convertInputToDate
     * @param desc The description of the Deadline task.
     * @param by The String representation of the deadline to parse into a LocalDateTime object.
     * @throws DateTimeParseException if the deadline cannot be parsed by TaskDateHandler.
     */
    public Deadline(String desc, String by) throws DateTimeParseException {
        super(desc);
        this.by = TaskDateHandler.convertInputToDate(by);
    }

    /**
     * Constructs a Deadline using a String array, for use in save parsing.
     * @see trackerbot.utils.TaskDateHandler#convertSaveToDate
     * @param args The arguments for constructing Deadline, containing isDone status in index
     *             0, description in index 1 and an epoch Date string in index 2
     * @throws DateTimeParseException if the deadline cannot be parsed by TaskDateHandler.
     * @throws NumberFormatException if the epoch save string is corrupted.
     */
    protected Deadline(String[] args) throws DateTimeParseException, NumberFormatException {
        super(args);
        this.by = TaskDateHandler.convertSaveToDate(args[2]);
    }

    @Override
    public String toSaveString() {
        return "D|" + getSaveInfo() + "|" + this.by.toEpochSecond(ZoneOffset.UTC);
    }

    /**
     * toString method of Deadline. <br>
     * The String representation of To-do appends the [D] tag in front of the Task toString, and
     * the deadline date to the end of the toString.
     * @return "[D]" prefixed to task.toString(), and "(by: [deadline])" postfixed to task.toString().
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + TaskDateHandler.convertDateToUi(this.by) + ")";
    }
}
