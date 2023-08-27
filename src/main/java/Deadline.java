public class Deadline extends Task{
    private String deadline;
    public Deadline(String task, boolean isDone, String deadline) {
        super(task, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return "D|" + super.toSaveFormat() + String.format("|%s\n", deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", deadline);
    }
}
