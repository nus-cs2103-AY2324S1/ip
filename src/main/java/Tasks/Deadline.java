package Tasks;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getEndDateTimeString() {
        return this.by;
    }

    @Override
    public String replyString(int currNumOfTask) {
        return super.replyString(currNumOfTask);
    }
}