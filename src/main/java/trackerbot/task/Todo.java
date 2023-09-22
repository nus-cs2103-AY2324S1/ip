package trackerbot.task;

/**
 * The To-do class for TrackerBot, inheriting from the Task class. <br>
 * This represents the most basic variant of the Task, and can be constructed
 * with the basic parameters for Task.
 *
 * @author WZWren
 * @version A-JavaDoc
 */
public class Todo extends Task {
    /**
     * Constructs a To-do object from the given String input.
     *
     * @param desc The description of the To-do task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Constructs a To-do using a String array, for use in save parsing.
     * <p>As To-do serves as a basic abstraction of the Task, the constructor directly calls
     * super to generate the object.</p>
     *
     * @param args The arguments for constructing a To-do, containing isDone status in index
     *             0 and description in index 1.
     */
    protected Todo(String[] args) {
        super(args);
    }

    @Override
    public String toSaveString() {
        return "T|" + getSaveInfo();
    }

    /**
     * Returns the String representation of the To-do object.
     * <p>This method appends the [T] tag in front of the Task toString.</p>
     *
     * @return "[T]" appended to Task.toString().
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
