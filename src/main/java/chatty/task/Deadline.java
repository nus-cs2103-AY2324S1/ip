package chatty.task;

public class Deadline extends Task {

    private final String deadline;

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String status = (this.isDone) ? "[D][X] " : "[D][ ] ";
        String task = String.format("%s (by: %s)", this.task, this.deadline);
        return status + task;
    }
}
