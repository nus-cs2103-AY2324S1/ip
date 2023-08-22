public class Deadline extends Task {

    /** Deadline of task. */
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline= deadline;
    }

    /**
     * Display string representation of task with deadline.
     *
     * @return String representation of task with deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadline + ")";
    }
}
