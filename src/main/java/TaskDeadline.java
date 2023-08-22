public class TaskDeadline extends Task {
    String deadlineDate;
    TaskDeadline(Parser input) {
        super(input);
        super.oneLetterAbbrev = "D";
        this.deadlineDate = input.getTaggedInput("by");
    }
    @Override
    public String toString() {
        return super.toString() + 
        " (by: " + this.deadlineDate + ")";
    }
}
