package Tasks;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public void printStatus() {
        System.out.printf("[D][%s] %s (by: %s)\n", this.isDone ? "X" : " ", this.description, this.deadline);
    }
}
