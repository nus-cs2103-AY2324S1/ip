public class Deadline extends Task {
    protected String deadline;
    public Deadline (String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline (String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " +  this.description + " (by: " + this.deadline + ")";
    }
}
