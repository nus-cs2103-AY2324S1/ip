public class DeadlineTask extends Task {

    protected String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    String DEADLINE_TASK_TYPE = "[D]";

    public String getDeadline() {
        return String.format(" (by: %s)", this.deadline);
    }

    public String toString() {
        return DEADLINE_TASK_TYPE + super.toString() + this.getDeadline();
    }
}