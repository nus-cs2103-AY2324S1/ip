public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                this.getStatusIcon(),
                this.description,
                this.deadline
        );
    }

    @Override
    public String toFile() {
        return String.format("D | %s | %s | %s",
                    this.isDone ? "1" : "0", this.description, this.deadline);
    }

}
