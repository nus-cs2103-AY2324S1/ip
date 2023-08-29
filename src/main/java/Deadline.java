public class Deadline extends Task {

    /**
     * The deadline of the task
     */
    private final String by;

    /**
     * constructor for Deadline
     *
     * @param by    - the deadline of the task
     * @param task  - the description of the task created
     * @param input - Input that generated the task
     */
    public Deadline(String by, String task, String input) {
        super(task, input);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
