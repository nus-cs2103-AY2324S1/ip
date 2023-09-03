package rat.tasks;

/**
 * This class encapsulates a ToDo task.
 * @author Keagan
 */
public class ToDo extends Task {

    /**
     * Constructor for a ToDo task.
     * @param name The name of the task.
     */
    protected ToDo(String name) {
        super(name);
    }

    /**
     * Returns a String representation of a ToDo task.
     * The String representation of a ToDo task is its name prefixed by its status and type.
     * @return A String representation of a ToDo task.
     */
    @Override
    public String toString() {
        String taskType = "[T]";
        return taskType + super.toString();
    }

    /**
     * Returns a String representation of a ToDo task that is used to write to a file.
     * @return The String representation of a ToDo task in the format used to write to a file.
     */
    @Override
    public String formatForFile() {
        String taskType = "T";
        return taskType + ", " + super.formatForFile();
    }

}
