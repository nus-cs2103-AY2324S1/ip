public class Deadline extends Task{
    private String deadline;

    /**
     * A constructor to create a deadline task instance.
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * String representation of the task.
     * @return Desired representation of the task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.deadline + ")";
    }
}
