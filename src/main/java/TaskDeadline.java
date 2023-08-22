public class TaskDeadline extends Task {
    String deadlineDate;
    TaskDeadline(String taskName, String deadlineDate) {
        super(taskName);
        super.oneLetterAbbrev = "D";
        this.deadlineDate = deadlineDate;
    }
    @Override
    public String toString() {
        return super.toString() + 
        " (by: " + this.deadlineDate + ")";
    }
}
