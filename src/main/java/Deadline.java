public class Deadline extends Task {
    private final String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String checkBox = this.done ? "[D][X] " : "[D][ ] ";
        String description = String.format("%s (by: %s)", this.task, this.deadline);
        return checkBox + description;
    }
}
