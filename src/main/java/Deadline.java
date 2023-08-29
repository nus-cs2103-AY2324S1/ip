public class Deadline extends Task {

    /**
     * The deadline of the task
     */
    private final String by;

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

    /**
     * constructor for Deadline from storage
     *
     * @param by        - the deadline of the task
     * @param task      - the description of the task created
     * @param completed - if completed
     */
    public Deadline(String by, String task, boolean completed) {
        super(task);
        this.by = by;
        if (completed) {
            this.toggleCompleted();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * returns the stored form of this deadline
     * Stored as DEADLINE,{task description},{by}
     *
     * @return DEADLINE,{task description},{by}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[]{"DEADLINE", this.getTask(), this.isCompleted() ? "1" : "0", this.by});
    }

}
