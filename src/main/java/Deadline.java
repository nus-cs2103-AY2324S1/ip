public class Deadline extends Task {

    protected String deadline;

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String saveToFileString(){
        return "D " + (super.isDone ? "| 1 | " : "| 0 | ") + super.toString() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + super.toString() + " (by: " + deadline + ")";
    }
}
