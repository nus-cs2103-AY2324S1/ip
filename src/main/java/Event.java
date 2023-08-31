/**
 * The Event class for TrackerBot, inheriting from the Task class. <br>
 * This Event child contains the start and end times to complete the task by.
 *
 * @author WZWren
 * @version Level-4
 */
public class Event extends Task {
    /** The start date of the event. **/
    String from;

    /** The end date of the event. **/
    String to;

    /**
     * The constructor for the class.
     * @param desc The description of the Deadline task.
     */
    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    protected Event(String[] args) {
        super(args);
        this.from = args[2];
        this.to = args[3];
    }

    @Override
    public String toSaveString() {
        return "E|" + getSaveInfo() + "|" + this.from + "|" + this.to;
    }

    /**
     * toString method of Event. <br>
     * The String representation of To-do appends the [E] tag in front of the Task toString, and
     * the event period to the end of the toString.
     * @return "[D]" prefixed to task.toString(), and "(from: [start] / to: [end])" postfixed to task.toString().
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " | to: " + this.to + ")";
    }
}
