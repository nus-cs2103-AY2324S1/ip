package rat.tasks;

/**
 * This class encapsulates an Event task.
 * An Event task is a task with a start time and end time as Strings.
 * @author Keagan
 */
public class Event extends Task {

    /**
     * The start time of the task, represented by a String.
     */
    private String startTime;

    /**
     * The end time of the task, represented by a String.
     */
    private String endTime;

    /**
     * Constructor for an Event task.
     * @param startTime The start time of the task.
     * @param endTime The end time of the task.
     * @param name The name of the task.
     */
    protected Event(String startTime, String endTime, String name) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a String representation of an Event task.
     * The String representation of an Event task is its name prefixed by its status and type.
     * @return A String representation of an Event task.
     */
    @Override
    public String toString() {
        String taskType = "[E]";
        return taskType + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    @Override
    public String formatForFile() {
        String taskType = "E";
        return taskType + ", " + super.formatForFile() + ", " + this.startTime + ", " + this.endTime;
    }

}
