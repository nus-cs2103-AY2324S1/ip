import ip.utils.TaskDateHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class for TrackerBot, inheriting from the Task class. <br>
 * This Task child contains the deadline to complete the task by.
 *
 * @author WZWren
 * @version Level-4
 */
public class Deadline extends Task {
    /** The Deadline of the task. **/
    LocalDateTime by;

    /**
     * The constructor for the class.
     * @param desc The description of the Deadline task.
     */
    public Deadline(String desc, String by) throws DateTimeParseException {
        super(desc);
        this.by = TaskDateHandler.convertInputToDate(by);
    }

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
