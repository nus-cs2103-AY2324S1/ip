/**
 * Deadline tasks have a due date in addition to their task and status.
 */
public class Deadline extends Task {

    /**
     * The due date for an instance of a Deadline task.
     */
    protected String byDate;

    /**
     * Instantiates an instance of a Deadline task class.
     * @param taskname Name of the task.
     * @param byDate Due date.
     */
    public Deadline(String taskname, String byDate) {
        super(taskname);
        this.byDate = byDate;
    }

    /**
     * Shows the name of the Deadline task and its status.
     *
     * @return Name of the Deadline task, due date and its current status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
}
