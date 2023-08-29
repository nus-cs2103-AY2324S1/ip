public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
