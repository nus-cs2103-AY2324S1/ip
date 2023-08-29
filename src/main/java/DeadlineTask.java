public class DeadlineTask extends Task {
    private final String deadline;
    public DeadlineTask(String desc, String deadline) {
        super(desc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String getSaveFormat() {
        return String.format(
                "D | %s | %s",
                super.getSaveFormat(),
                this.deadline
        );
    }
}
