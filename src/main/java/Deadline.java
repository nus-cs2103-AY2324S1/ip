public class Deadline extends Task{

    /**
     * The due date for an instance of a Deadline task.
     */
    protected String by;

    /**
     * Instantiates an instance of a Deadline task class.
     * @param taskname Name of the task.
     * @param by Due date.
     */
    public Deadline(String taskname, String by) {
        super(taskname);
        this.by = by;
    }

    /**
     * Shows the name of the Deadline task and its status.
     *
     * @return Name of the Deadline task and its current status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
