public class DeadlineTask extends Task {
    private String deadlineDate;
    public DeadlineTask(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }
}