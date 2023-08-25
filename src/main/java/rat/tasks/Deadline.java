package rat.tasks;

/**
 * This class encapsulates a Deadline task.
 * A Deadline task is a task with a deadline as a String.
 * @author Keagan
 */
public class Deadline extends Task {

    /**
     * The deadline of the task, represented by a String.
     */
    private String deadline;

    /**
     * Constructor for a Deadline task.
     * @param deadline The deadline of the task.
     * @param name The name of the task.
     */
    protected Deadline(String deadline, String name) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Returns a String representation of a Deadline task.
     * The String representation of a Deadline task is its name prefixed by its status and type.
     * @return A String representation of a Deadline task.
     */
    @Override
    public String toString() {
        String taskType = "[D]";
        return taskType + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String formatForFile() {
        String taskType = "D";
        return taskType + ", " + super.formatForFile() + ", " + this.deadline;
    }

}
