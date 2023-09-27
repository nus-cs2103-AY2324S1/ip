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

    @Override
    public String toString() {
        String taskType = "[T]";
        return taskType + super.toString();
    }

    @Override
    public String formatForFile() {
        String taskType = "T";
        return taskType + ", " + super.formatForFile();
    }

}
