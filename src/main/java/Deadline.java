/**
 * The Deadline class for TrackerBot, inheriting from the Task class. <br>
 * This Task child contains the deadline to complete the task by.
 *
 * @author WZWren
 * @version Level-4
 */
public class Deadline extends Task {
    /** The Deadline of the task. **/
    String by;

    /**
     * The constructor for the class.
     * @param desc The description of the Deadline task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(String[] args) {
        super(args);
        this.by = args[2];
    }

    @Override
    public String toSaveString() {
        return "D|" + getSaveInfo() + "|" + this.by;
    }

    /**
     * toString method of Deadline. <br>
     * The String representation of To-do appends the [D] tag in front of the Task toString, and
     * the deadline date to the end of the toString.
     * @return "[D]" prefixed to task.toString(), and "(by: [deadline])" postfixed to task.toString().
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
