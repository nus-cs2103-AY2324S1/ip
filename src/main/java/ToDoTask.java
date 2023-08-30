/**
 * Encapsulates a task without any time/date attached.
 * Inherits from Task class.
 * @author Wu Jingya
 */
public class ToDoTask extends Task{

    /**
     * Creates a ToDo Task with the specified description
     * @param description The task's description
     */
    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, Boolean done) {
        super(description, done);
    }

    /**
     * Returns the string representation of the task
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T|" + super.toData();
    }
}
