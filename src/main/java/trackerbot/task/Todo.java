package trackerbot.task;

/**
 * The To-do class for TrackerBot, inheriting from the Task class. <br>
 * This represents the most basic variant of the Task, and can be constructed
 * with the basic parameters for Task.
 *
 * @author WZWren
 * @version Level-4
 */
public class Todo extends Task {
    /**
     * The constructor for the class.
     * @param desc The description of the To-do task.
     */
    public Todo(String desc) {
        super(desc);
    }

    public Todo(String[] args) {
        super(args);
    }

    @Override
    public String toSaveString() {
        return "T|" + getSaveInfo();
    }

    /**
     * toString method of To-do. <br>
     * The String representation of To-do appends the [T] tag in front of the Task toString.
     * @return "[T]" appended to task.toString().
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
