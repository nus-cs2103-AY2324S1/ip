public class DeadlinesTask extends Task {
    private final String deadline;
    public DeadlinesTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String typeIcon = "[D]";
        return String.format("%s%s (by: %s)", typeIcon, super.toString(), this.deadline);
    }
}
