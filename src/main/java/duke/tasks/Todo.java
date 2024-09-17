package duke.tasks;

/**
 * Represent Todo Class
 */
public class Todo extends Task {
    /**
     * Creates new Todo Object
     *
     * @param description of todo Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Added todo message
     *
     * @return String added message and total number of tasks
     */
    public String addedMessage() {
        String ret = "";
        ret += "Got it. I've added this task:\n";
        ret += "  " + this + "\n";
        ret += "Now you have " + super.size + " tasks in the list.\n";
        return ret;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " #" + super.tag;
    }
}