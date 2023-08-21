public class Deadline extends Task {

    /**
     * The deadline of the task
     */
    private String by;

    /**
     * constructor for Deadline
     * 
     * @param by    - the deadline of the task
     * @param task  - the description of the task created
     * @param index - the id of the task created
     */
    public Deadline(String by, String task, int index) {
        super(task, index);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
