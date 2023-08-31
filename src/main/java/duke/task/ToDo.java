package duke.task;

/**
 * Encapsulates a ToDo task that can be added to the task manager.
 *
 * @author Teo Kai Sheng
 */
public class ToDo extends Task {

    /**
     * Constructor to create a ToDo.
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the ToDo.
     * @return A String representing the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of the ToDo to be saved in the hard disk.
     * @return A String representing the ToDo.
     */
    @Override
    public String taskToString() {
        return "T | " + super.taskToString();
    }
}
