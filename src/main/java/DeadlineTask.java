public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + deadline + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " | " + deadline;
    }
}
