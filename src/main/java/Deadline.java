public class Deadline extends Task {

    /**
     * The deadline of the task
     */
    private String by;

    /**
     * constructor for Deadline
     * 
     * @param by   - the deadline of the task
     * @param task - the description of the task created
     */
    public Deadline(String by, String task) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
