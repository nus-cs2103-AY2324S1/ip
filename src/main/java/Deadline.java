public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.task + " (by: " + this.deadline + ")";
        }
        return "[D][ ] " + this.task + " (by: " + this.deadline + ")";
    }
}
