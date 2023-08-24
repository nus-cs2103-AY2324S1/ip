public class Deadline extends Task{

    /** Date that task is to be completed by. */
    private String by;

    /**
     * Constructor for Deadline.
     * @param taskName Name of deadline.
     * @param by Date where the task is due.
     */
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the string representation of the status of the task
     * @return Status of the task
     */
    @Override
    public String getTask() {
        return "Deadline ->" + super.getTask() + " By: " + this.by;
    }
}
